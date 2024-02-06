package com.mooc.ludotheque.services.impl;

import com.mooc.ludotheque.exceptions.EntityDoesntExistException;
import com.mooc.ludotheque.models.Driver;
import com.mooc.ludotheque.models.Race;
import com.mooc.ludotheque.repositories.DriverRepository;
import com.mooc.ludotheque.repositories.RaceRepository;
import com.mooc.ludotheque.services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceServiceImpl implements RaceService {

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private DriverRepository driverRepository;


    @Override
    public Race findById(int id) {

        Optional<Race> race = raceRepository.findById(id);

        if (race.isPresent()) return race.get();

        throw new EntityDoesntExistException();
    }

    @Override
    public int create(Race race) {
        return raceRepository.save(race).getId();
    }

    @Override
    public void assignDriver(int raceId, int driverId) {
        Optional<Race> optionalRace = raceRepository.findById(raceId);

        Optional<Driver> optionalDriver = driverRepository.findById(driverId);

        if (optionalRace.isPresent() && optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            Race race  =optionalRace.get();

            driver.setRace(race);

            driverRepository.save(driver);
        }

        throw new EntityDoesntExistException();
    }

    @Override
    public String startRace(int id) {

        Optional<Race> optionalRace = raceRepository.findById(id);

        if (optionalRace.isPresent()) {
            Race race = optionalRace.get();
            List<Driver> drivers = race.getDrivers();
            Driver winner = null;
            
            for (Driver driver : drivers) {
                if (winner == null || driver.getCar().getSpeed() > winner.getCar().getSpeed()) {
                    winner = driver;
                }
            }

            if (winner == null) return "La course ne peut pas délarrer avec 0 pilotes !";
            else return "La course a bien démarré ! Et le gagnant est " + winner.getFirstName() + " " + winner.getLastName() + " !";
        }

        throw new EntityDoesntExistException();
    }
}
