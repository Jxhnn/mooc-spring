package com.mooc.ludotheque.services.impl;

import com.mooc.ludotheque.exceptions.EntityDoesntExistException;
import com.mooc.ludotheque.models.Car;
import com.mooc.ludotheque.models.Driver;
import com.mooc.ludotheque.repositories.CarRepository;
import com.mooc.ludotheque.repositories.DriverRepository;
import com.mooc.ludotheque.repositories.RaceRepository;
import com.mooc.ludotheque.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    RaceRepository raceRepository;

    @Autowired
    CarRepository carRepository;

    @Override
    public List<Driver> findAll() {
        List<Driver> drivers = new ArrayList<>();
        driverRepository.findAll().forEach(drivers::add);

        return drivers;
    }

    @Override
    public Driver findById(int id) {

        Optional<Driver> driver = driverRepository.findById(id);

        if (driver.isPresent()) return driver.get();
        throw new EntityDoesntExistException();
    }

    @Override
    public void update(int id, Driver existingDriver, Driver newDriver) {

        if (newDriver.getFirstName() != null) {
            existingDriver.setFirstName(newDriver.getFirstName());

        }

        if (newDriver.getLastName() != null) {
            existingDriver.setLastName(newDriver.getLastName());
        }

        if (newDriver.getRace() != null) {
            existingDriver.setRace(newDriver.getRace());
        }

        if (newDriver.getCar() != null) {
            existingDriver.setCar(newDriver.getCar());
        }

        driverRepository.save(existingDriver);
    }

    @Override
    public int create(Driver driver) {

        return driverRepository.save(driver).getId();
    }

    @Override
    public void delete(Driver driver) {
        driverRepository.delete(driver);
        driver.getRace().getDrivers().remove(driver);
    }

    @Override
    public void assignCar(int driverId, int carId) {

        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        Optional<Car> optionalCar = carRepository.findById(carId);

        if (optionalDriver.isPresent() && optionalCar.isPresent()) {
            Driver driver = optionalDriver.get();
            Car car = optionalCar.get();

            driver.setCar(car);
            driverRepository.save(driver);
        }

        throw new EntityDoesntExistException();
    }
}
