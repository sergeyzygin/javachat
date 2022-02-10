package com.lsoft.chat.utils;

import java.io.IOException;
import java.io.InputStream;

import com.lsoft.chat.data.models.File;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

public class S3Util {
    private static final String BUCKET = "testfilebucketzyg";
    private S3Client client;
    public S3Util(){
        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                "AKIA2GNG5AQ57336GIOD",
                "DG3AjT4w4kFo5a2zMCC3iWNK+MVPCOi/rB2XvQPK");
        this.client = S3Client.builder().credentialsProvider(StaticCredentialsProvider.create(credentials)).region(Region.US_EAST_1).build();

    }
    public void uploadFile(String fileName, InputStream inputStream)
            throws S3Exception, AwsServiceException, SdkClientException, IOException {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(fileName)
                .build();

        PutObjectResponse response = this.client.putObject(request,
                RequestBody.fromInputStream(inputStream, inputStream.available()));
        GetUrlRequest urequest = GetUrlRequest.builder().bucket(BUCKET).key(fileName).build();
        String url = this.client.utilities().getUrl(urequest).toExternalForm();
        String s = url.toString();

    }

    public ResponseEntity<byte[]> download(String fileName, File file) throws IOException {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(BUCKET)
                .key(fileName)
                .build();

        byte[] bytes = IOUtils.toByteArray(this.client.getObject(request));


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        if (file.getType() != null && file.getType().equals("audio")) {
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return ResponseEntity.ok()
                    .headers(httpHeaders)
                    .contentLength(bytes.length)
                    .header("Content-Type", "audio/mpeg")

                    //.contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(bytes);
        } else {
            httpHeaders.setContentDispositionFormData("attachment", fileName);

            return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
        }
    }
/*
    public ResponseEntity download(String fileName) {
            GetObjectRequest request = GetObjectRequest.builder()
                    .bucket(BUCKET)
                    .key(fileName)
                    .build();
            ResponseInputStream a = this.client.getObject(request);
        HttpHeaders headers = new HttpHeaders();
        //headers.setContentLength(Files.size(Paths.get(filePath)));
            return new ResponseEntity(a, HttpStatus.OK);

    }*/
}

