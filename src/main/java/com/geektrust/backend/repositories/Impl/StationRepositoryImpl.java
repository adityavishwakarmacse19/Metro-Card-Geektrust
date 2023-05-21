package com.geektrust.backend.repositories.Impl;

import java.util.HashMap;
import java.util.Map;
import com.geektrust.backend.models.Station;
import com.geektrust.backend.repositories.StationRepository;
import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.dto.StationCollection;

public class StationRepositoryImpl implements StationRepository {
    private Map<StationName, Station> stationMap = new HashMap<>();

    @Override
    public void save(Station station) {
        stationMap.put(station.getStationName(), station);
    }

    @Override
    public Station getByStationName(StationName stationName) {
        return stationMap.get(stationName);
    }

    @Override
    public void addCollection(StationCollection stationCollection) {
        StationName stationName = stationCollection.getStationName();
        Station station = stationMap.get(stationName);

        Passenger passenger = stationCollection.getPassenger();

        if(station != null){
            Map<Passenger, Integer> passengerMap = station.getPassengerMap();
            if(passengerMap.containsKey(passenger)){
                int passengerCount = passengerMap.get(passenger);
                station.getPassengerMap().put(passenger, passengerCount+1);
            } else {
                station.getPassengerMap().put(passenger, 1);
            }
            //passengerMap of station updated

            Station collected = new Station(stationName, station.getTotalDiscount() + stationCollection.getDiscount(), station.getTotalCharges() + stationCollection.getCost(), station.getPassengerMap());
            stationMap.put(stationName, collected);
        } else {
            Map<Passenger, Integer> passengerMap = new HashMap<>();
            passengerMap.put(passenger, 1);
            Station collected = new Station(stationCollection.getStationName(), stationCollection.getDiscount(), stationCollection.getCost(), passengerMap);
            stationMap.put(stationName, collected);
        }

    }

    @Override
    public void delete(Station station) {
        stationMap.remove(station.getStationName());
    }

}
