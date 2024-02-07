package com.Huy.AutoWeb.Utils;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/s3")
public class AmazonS3Controller {

    private static final String MESSAGE_1 = "Uploaded the file successfully";
    private static final String FILE_NAME = "fileName";

    @Autowired
    AmazonS3Service s3Service;

    @PostMapping("/upload")
    public void uploadFile(@RequestParam String key,
                           @RequestParam MultipartFile file) throws IOException {
        s3Service.uploadFile( key, convert(file));
    }

    @GetMapping("/download")
    public S3Object downloadFile( @RequestParam String key) {
        return s3Service.downloadFile( key);
    }

    @GetMapping("/list")
    public ObjectListing listObjects() {
        return s3Service.listObjects();
    }

    private File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        file.transferTo(convFile);
        return convFile;
    }

}