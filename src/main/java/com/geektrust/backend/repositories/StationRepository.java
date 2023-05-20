package com.geektrust.backend.repositories;

import com.geektrust.backend.models.Station;
import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.dto.StationCollection;

public interface StationRepository {
    Station getByStationName(StationName stationName);
    void save(Station station);
    void addCollection(StationCollection stationCollection);
    void delete(Station station);
}
