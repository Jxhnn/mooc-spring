package com.mooc.ludotheque.controllers;

import com.mooc.ludotheque.models.Race;
import com.mooc.ludotheque.services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RaceController {

    @Autowired
    private RaceService raceService;

    @GetMapping("/race/{id}")
    public Race findById(@PathVariable("id") int id) {
        return raceService.findById(id);
    }

    @PostMapping("/race")
    @ResponseStatus(code = HttpStatus.CREATED)
    public int create(@RequestBody Race race) {
        return raceService.create(race);
    }

    @PutMapping("/race/{race_id}/driver/{driver_id}")
    public void assignRaceToDriver(@PathVariable("race_id") int race_id, @PathVariable("driver_id") int driver_id) {
        raceService.assignDriver(race_id, driver_id);
    }

    @PutMapping("/race/{id}/speed/start")
    public String startRace(@PathVariable("id") int id) {
        return raceService.startRace(id);
    }
}
