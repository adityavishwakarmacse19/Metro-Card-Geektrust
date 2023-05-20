package com.geektrust.backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.geektrust.backend.models.Station;
import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.constants.Passenger;

public class StationRepositoryTest {

    StationRepository stationRepository;
    Station station;
    Map<Passenger, Integer> passengerMap = new HashMap<>();
    Passenger passenger = Passenger.ADULT;
    
    @BeforeEach                                         
    void setUp() {
        stationRepository = new StationRepositoryImpl();
        passengerMap.put(passenger, 1);
        station = new Station(StationName.CENTRAL, 100, 500, passengerMap);
        stationRepository.save(station);
    }

    @Test
    public void save() {
        assertEquals(stationRepository.getByStationName(StationName.CENTRAL), station);
    }

    @Test
    public void getByStationName() {
        assertEquals(stationRepository.getByStationName(StationName.CENTRAL), station);
    }

    @Test
    void delete() {
        stationRepository.delete(station);
        assertNull(stationRepository.getByStationName(StationName.CENTRAL));
    }

}
