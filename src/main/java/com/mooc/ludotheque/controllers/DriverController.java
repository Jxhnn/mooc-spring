package com.mooc.ludotheque.controllers;

import com.mooc.ludotheque.exceptions.EntityDoesntExistException;
import com.mooc.ludotheque.models.Driver;
import com.mooc.ludotheque.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/drivers")
    public List<Driver> findAll() {

        return driverService.findAll();
    }

    @PostMapping("/driver")
    @ResponseStatus(code = HttpStatus.CREATED)
    public int create(@RequestBody Driver driver) {

        return driverService.create(driver);
    }

    @PatchMapping("/driver/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") int id, @RequestBody Driver newDriver) {
        Driver existingDriver = driverService.findById(id);

        driverService.update(id, existingDriver, newDriver);
    }

    @PutMapping("/driver/{driver_id}/car/{car_id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void assignCar(@PathVariable("driver_id") int driverId, @PathVariable("car_id") int carId) {

        driverService.assignCar(driverId, carId);
    }

    @DeleteMapping("/driver/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable("id") int id) {

        Driver driver = driverService.findById(id);

        if (driver == null) {
            throw new EntityDoesntExistException();
        }

        driverService.delete(driver);
    }
}
