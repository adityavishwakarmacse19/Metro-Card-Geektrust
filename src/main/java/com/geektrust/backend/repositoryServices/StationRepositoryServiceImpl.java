package com.geektrust.backend.repositoryServices;

import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.dto.StationCollection;
import com.geektrust.backend.models.Station;
import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.repositories.StationRepository;
import com.geektrust.backend.repositories.StationRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

public class StationRepositoryServiceImpl implements StationRepositoryService{

    StationRepository stationRepository = new StationRepositoryImpl();

    @Override
    public Station getByStationName(StationName stationName) {
        return stationRepository.getByStationName(stationName);
    }

    @Override
    public void save(Station station) {
        stationRepository.save(station);
    }
    
    @Override
    public void addCollection(StationCollection stationCollection) {
        StationName stationName = stationCollection.getStationName();
        Station station = stationRepository.getByStationName(stationName);

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
            stationRepository.save(collected);
        } else {
            Map<Passenger, Integer> passengerMap = new HashMap<>();
            passengerMap.put(passenger, 1);
            Station collected = new Station(stationCollection.getStationName(), stationCollection.getDiscount(), stationCollection.getCost(), passengerMap);
            stationRepository.save(collected);
        }

    }

	@Override
	public void delete(Station station) {
		stationRepository.delete(station);
	}

}
