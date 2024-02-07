package com.Huy.AutoWeb.Service;

import com.Huy.AutoWeb.Entity.Car;
import com.Huy.AutoWeb.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl {

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

    public void deleteCar(String carId) {
        carRepository.deleteById(carId);
    }
}

