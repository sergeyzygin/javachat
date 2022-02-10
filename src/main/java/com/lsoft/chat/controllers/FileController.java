package com.lsoft.chat.controllers;

import com.lsoft.chat.data.models.File;
import com.lsoft.chat.data.repositories.FileRepository;
import com.lsoft.chat.services.AuthService;
import com.lsoft.chat.utils.S3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
//@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/file")
public class FileController {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    AuthService authService;

    @GetMapping(value = "/{key}", produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity viewHomePage(@PathVariable String key) {
        try {
            File file = fileRepository.findByAwskey(key);
            return new S3Util().download(key, file);
        } catch (Exception e){
            return null;
        }
    }

    @PostMapping("/upload")
    public File uploadFile(@RequestParam("filename")String filename,
                                   @RequestParam("file") MultipartFile multipart,
                                   @RequestParam(value ="type", required=false) String type,
                                   @RequestParam(value="ext", required=false)String ext,
                                   @RequestParam(value = "size", required = false, defaultValue = "0")int size) {
        //String fileName = filename;//multipart.getOriginalFilename();
        File file = new File();

        try {

            UUID uuid = UUID.randomUUID();
            String key;
            if (ext != null) key = uuid.toString() + "." +ext;
            else key = uuid.toString();
            file.setAwskey(key);
            file.setName(filename);
            file.setExt(ext);
            file.setType(type);
            //file.setOwner(authService.getCurrentUser().getId());
            file.setSize(size);
            new S3Util().uploadFile(key, multipart.getInputStream());
            fileRepository.save(file);
            return file;
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Error upload"
            );
            //message = "Error uploading file: " + ex.getMessage();
        }
    }
}
