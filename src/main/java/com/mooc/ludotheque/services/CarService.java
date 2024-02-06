package com.mooc.ludotheque.services;

import com.mooc.ludotheque.models.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    int create(Car car);
}
