package com.geektrust.backend.Repositories;

import java.util.HashMap;
import java.util.Map;

import com.geektrust.backend.Services.MetroServiceImpl.Passenger;
import com.geektrust.backend.dto.StationCollection;
import com.geektrust.backend.models.Station;
import com.geektrust.backend.models.Station.StationName;;

public class CollectionRepositoryImpl implements CollectionRepository {
    Map<StationName, Station> stationMap = new HashMap<>();

    @Override
    public Station getByStationName(StationName stationName) {
        return stationMap.get(stationName);
    }

    @Override
    public Station addCollection(StationCollection stationCollection) {
        Station station2 = stationMap.get(stationCollection.getStationName());

        Passenger passenger = stationCollection.getPassenger();

        if(station2 != null){
            if(station2.getPassengerMap().containsKey(stationCollection.getPassenger())){
                int passengerCount = station2.getPassengerMap().get(passenger);
                station2.getPassengerMap().put(passenger, passengerCount+1);
            } else {
                station2.getPassengerMap().put(passenger, 1);
            }
            Station collected = new Station(stationCollection.getStationName(), station2.getTotalDiscount() + stationCollection.getDiscount(), station2.getTotalCharges() + stationCollection.getCost(), station2.getPassengerMap());
            return stationMap.put(stationCollection.getStationName(), collected);
        } else {
            Map<Passenger, Integer> passengerMap = new HashMap<>();
            passengerMap.put(stationCollection.getPassenger(), 1);
            Station toAdd = new Station(stationCollection.getStationName(), stationCollection.getDiscount(), stationCollection.getCost(), passengerMap);
            return stationMap.put(stationCollection.getStationName(), toAdd);
        }

    }


}
