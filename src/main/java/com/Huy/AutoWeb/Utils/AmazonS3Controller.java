package com.Huy.AutoWeb.Utils;

import com.Huy.AutoWeb.Entity.Car;
import com.Huy.AutoWeb.Service.CarServiceImpl;
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
    CarServiceImpl carService;
    @Autowired
    AmazonS3Service s3Service;

    @PostMapping("/upload/{id}")
    public ResponseEntity<String> uploadFile(@PathVariable String id,@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(id);
        Car car = carService.getCarById(id).orElse(null);
        car.getImageUrl().add(file.getOriginalFilename());
        System.out.println(car);
        carService.saveCar(car);
        s3Service.uploadFile(id,file);
        return ResponseEntity.ok().body( file.getOriginalFilename()+"file upload successfully");
    }
    @PostMapping("/uploadDemo")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file){
        return ResponseEntity.ok().body("file received successfully");
    }

    @GetMapping("/download")
    public S3Object downloadFile( @RequestParam String key) {
        return s3Service.downloadFile( key);
    }

    @GetMapping("/list")
    public ObjectListing listObjects() {
        return s3Service.listObjects();
    }

}