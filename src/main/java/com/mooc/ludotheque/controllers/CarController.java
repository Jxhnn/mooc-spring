package com.mooc.ludotheque.controllers;

import com.mooc.ludotheque.models.Car;
import com.mooc.ludotheque.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public List<Car> findAll() {
        return carService.findAll();
    }

    @PostMapping("/car")
    @ResponseStatus(code = HttpStatus.CREATED)
    public int create(@RequestBody Car car) {

        return carService.create(car);
    }
}
