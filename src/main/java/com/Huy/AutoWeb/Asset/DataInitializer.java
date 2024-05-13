package com.Huy.AutoWeb.Asset;

import com.Huy.AutoWeb.Entity.Car;
import com.Huy.AutoWeb.Entity.User;
import com.Huy.AutoWeb.Repository.CarRepository;
import com.Huy.AutoWeb.Repository.UserRepository;
import com.Huy.AutoWeb.Service.CarServiceImpl;
import com.Huy.AutoWeb.Service.UserServiceImpl;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserServiceImpl userService;
    private final CarServiceImpl carService;

    private CarRepository carRepository;
    private UserRepository userRepository;

    @Autowired
    public DataInitializer(UserServiceImpl userService, CarServiceImpl carService) {
        this.userService = userService;
        this.carService = carService;
    }

    @Override
    public void run(String... args) {
        // Generate and add fake users
          //  generateFakeUsers();

        // Generate and add fake cars
           // generateFakeCars();

    }

    private void generateFakeUsers() {
        Faker faker = new Faker();

        for (int i = 0; i < 10; i++) {
            User fakeUser = new User();
            fakeUser.setUsername(faker.name().username());
            fakeUser.setPassword(faker.internet().password());
            fakeUser.setEmail(faker.internet().emailAddress());
            fakeUser.setFullName(faker.name().fullName());

            userService.createUser(fakeUser);
        }
    }

    private void generateFakeCars() {
        Faker faker = new Faker();

        for (int i = 0; i < 10; i++) {
            Car fakeCar = new Car();

            fakeCar.setMake(faker.company().name());
            fakeCar.setModel(fakeCar.getModel());
            fakeCar.setYear(faker.number().numberBetween(2000, 2022));
            fakeCar.setPrice(faker.number().randomDouble(2, 1000, 50000));
            fakeCar.setMileage(faker.number().numberBetween(0, 100000));

            fakeCar.setFuelType(randomElement("Gasoline", "Diesel", "Electric"));
            fakeCar.setTransmission(randomElement("Automatic", "Manual"));
            fakeCar.setColor(faker.color().name());
            fakeCar.setDescription(faker.lorem().sentence());
            List<String> imageUrls = new ArrayList<>();
            for (int j = 0; j < faker.random().nextInt(1, 5); j++) {
                imageUrls.add(faker.internet().url());
            }
            fakeCar.setImageUrl(imageUrls);

            carService.createCar(fakeCar);
        }
    }
    private <T> T randomElement(T... elements) {
        Random random = new Random();
        return elements[random.nextInt(elements.length)];
    }
}

