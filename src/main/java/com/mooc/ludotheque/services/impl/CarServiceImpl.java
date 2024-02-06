package com.mooc.ludotheque.services.impl;

import com.mooc.ludotheque.models.Car;
import com.mooc.ludotheque.repositories.CarRepository;
import com.mooc.ludotheque.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findAll() {

        List<Car> cars = new ArrayList<>();

        carRepository.findAll().forEach(cars::add);

        return cars;
    }

    @Override
    public int create(Car car) {

        return carRepository.save(car).getId();
    }
}
