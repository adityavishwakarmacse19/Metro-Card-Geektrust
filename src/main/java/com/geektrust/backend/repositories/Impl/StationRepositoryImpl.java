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
        Station station = stationMap.get(stationCollection.getStationName());

        if(station != null){
            // Map<Passenger, Integer> passengerMap = station.getPassengerMap();
            // if(passengerMap.containsKey(passenger)){
            //     int passengerCount = passengerMap.get(passenger);
            //     station.getPassengerMap().put(passenger, passengerCount+1);
            // } else {
            //     station.getPassengerMap().put(passenger, 1);
            // }
            // //passengerMap of station updated

            // Station collected = new Station(stationName, station.getTotalDiscount() + stationCollection.getDiscount(), station.getTotalCharges() + stationCollection.getCost(), station.getPassengerMap());
            station.collect(stationCollection);
            stationMap.put(station.getStationName(), station);
        } else {
            Map<Passenger, Integer> passengerMap = new HashMap<>();
            passengerMap.put(stationCollection.getPassenger(), 1);
            Station newStation = new Station(stationCollection.getStationName(), stationCollection.getDiscount(), stationCollection.getCost(), passengerMap);
            
            stationMap.put(newStation.getStationName(), newStation);
        }

    }

    @Override
    public void delete(Station station) {
        stationMap.remove(station.getStationName());
    }

}
