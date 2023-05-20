package com.geektrust.backend.repositories;

import com.geektrust.backend.models.Station;
import com.geektrust.backend.constants.StationName;

public interface StationRepository {
    Station getByStationName(StationName stationName);
    void save(Station station);
    void delete(Station station);
}
