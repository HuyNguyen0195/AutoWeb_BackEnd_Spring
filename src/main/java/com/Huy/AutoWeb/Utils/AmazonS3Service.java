package com.Huy.AutoWeb.Utils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.model.Put;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AmazonS3Service {
    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucketName}")
    private String s3BucketName;

    public S3Object downloadFile( String key) {
        return amazonS3.getObject(new GetObjectRequest(s3BucketName, key));
    }

    public ObjectListing listObjects() {
        return amazonS3.listObjects(s3BucketName);
    }
    public String uploadFile(String id, MultipartFile file) throws IOException {

        String key = id+"/"+file.getOriginalFilename();
        File convertedFile = convert(id,file);

        amazonS3.putObject(new PutObjectRequest(s3BucketName, key , convertedFile));
        // Delete the temporary file
        convertedFile.delete();
        return id;
    }

    // Convert MultipartFile to File
    private File convert(String key,MultipartFile file) throws IOException {
        File convertedFile = new File(key);
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }
}
