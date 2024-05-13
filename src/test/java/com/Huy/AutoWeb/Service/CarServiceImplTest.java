package com.Huy.AutoWeb.Service;

import com.Huy.AutoWeb.Controller.CarController;
import com.Huy.AutoWeb.Entity.Car;
import com.amazonaws.services.mq.model.CreateUserRequest;
import com.amazonaws.services.mq.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CarController.class)
class CarServiceImplTest {

    @MockBean
    CarServiceImpl carService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createCar() {
        CreateUserRequest createUserRequest= new CreateUserRequest();
        createUserRequest.setUsername("tester");
        Car car =new Car();
    }
}