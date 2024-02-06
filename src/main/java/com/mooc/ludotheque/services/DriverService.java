package com.mooc.ludotheque.services;

import com.mooc.ludotheque.models.Driver;

import java.util.List;

public interface DriverService {

    List<Driver> findAll();

    Driver findById(int id);

    void update(int id, Driver existingDriver, Driver newDriver);

    int create(Driver driver);

    void delete(Driver driver);

    void assignCar(int driverId, int carId);
}
