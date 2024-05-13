package com.Huy.AutoWeb.Service;

import com.Huy.AutoWeb.Entity.Car;
import com.Huy.AutoWeb.Repository.CarRepository;
import com.Huy.AutoWeb.Utils.AmazonS3Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl {

    @Autowired
    AmazonS3Service amazonS3Service;

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(String carId) {
        return carRepository.findById(carId);
    }

    public Car createCar(Car car) {
        // Additional validation or business logic can be added here
        return carRepository.save(car);
    }

    public Car saveCar(Car car){
        return carRepository.save(car);
    }

    public Car createCar(Car car, MultipartFile file) {
        // Additional validation or business logic can be added here
        String id= new ObjectId().toString();
        car.setId(id);
        String key = id+"/"+file.getOriginalFilename();
        car.getImageUrl().add(key);
        try {
            amazonS3Service.uploadFile(key,file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Delete the temporary file
        return carRepository.save(car);
    }

    private File convert(String key,MultipartFile file) throws IOException {
        File convertedFile = new File(key);
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    public void deleteCar(String carId) {
        carRepository.deleteById(carId);
    }
}

