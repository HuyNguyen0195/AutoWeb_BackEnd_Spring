package com.Huy.AutoWeb.Utils;

import com.amazonaws.AmazonServiceException;
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

@Service
public class AmazonS3Service {
    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucketName}")
    private String s3BucketName;

    public void uploadFile( String key, File file) {
        amazonS3.putObject(new PutObjectRequest(s3BucketName, key, file));
    }

    public S3Object downloadFile( String key) {
        return amazonS3.getObject(new GetObjectRequest(s3BucketName, key));
    }

    public ObjectListing listObjects() {
        return amazonS3.listObjects(s3BucketName);
    }
}
