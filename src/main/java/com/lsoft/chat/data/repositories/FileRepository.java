package com.lsoft.chat.data.repositories;

import com.lsoft.chat.data.models.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<File, Integer> {
    File findByAwskey(String awskey);
}
