package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Map;

import com.geektrust.backend.dto.StationCollection;
import com.geektrust.backend.models.Station;
import com.geektrust.backend.models.Station.StationName;
import com.geektrust.backend.services.MetroServiceImpl.Passenger;;

public class StationRepositoryImpl implements StationRepository {
    Map<StationName, Station> stationMap = new HashMap<>();

    @Override
    public void save(Station station) {
        stationMap.put(station.getStationName(), station);
    }

    @Override
    public Station getByStationName(StationName stationName) {
        return stationMap.get(stationName);
    }

    @Override
    public void delete(Station station) {
        stationMap.remove(station.getStationName());
    }

}
