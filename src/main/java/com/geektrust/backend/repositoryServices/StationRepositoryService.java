package com.geektrust.backend.repositoryServices;

import com.geektrust.backend.dto.StationCollection;
import com.geektrust.backend.models.Station;
import com.geektrust.backend.models.Station.StationName;

public interface StationRepositoryService {
    Station getByStationName(StationName stationName);
    void save(Station station);
    void addCollection(StationCollection stationCollection);
    void delete(Station station);
}
