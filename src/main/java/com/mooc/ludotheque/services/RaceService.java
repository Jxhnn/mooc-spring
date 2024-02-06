package com.mooc.ludotheque.services;

import com.mooc.ludotheque.models.Race;

public interface RaceService {
    Race findById(int id);

    int create(Race race);

    void assignDriver(int raceId, int driverId);

    String startRace(int id);
}
