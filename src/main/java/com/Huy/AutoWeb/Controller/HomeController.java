package com.Huy.AutoWeb.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String greeting(){
        return "welcome to web back end api service by huy nguyen";
    }
}
