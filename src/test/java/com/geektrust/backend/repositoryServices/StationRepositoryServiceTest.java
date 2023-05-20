package com.geektrust.backend.repositoryServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.geektrust.backend.dto.StationCollection;
import com.geektrust.backend.models.Station;
import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.constants.Passenger;

public class StationRepositoryServiceTest {
    StationRepositoryService stationRepositoryService;
    Station station;
    Map<Passenger, Integer> passengerMap = new HashMap<>();
    Passenger passenger = Passenger.ADULT;
    
    @BeforeEach                                         
    void setUp() {
        stationRepositoryService = new StationRepositoryServiceImpl();
        passengerMap.put(passenger, 1);
        station = new Station(StationName.CENTRAL, 100, 500, passengerMap);
        stationRepositoryService.save(station);
    }

    @Test
    public void getByStationName() {
        assertEquals(stationRepositoryService.getByStationName(StationName.CENTRAL), station);
    }

    @Test
    public void save() {
        assertEquals(stationRepositoryService.getByStationName(StationName.CENTRAL), station);
    }


    @Test
    void addCollection() {
        StationCollection stationCollection = new StationCollection(StationName.AIRPORT, 200, 500, passenger);

        stationRepositoryService.addCollection(stationCollection);

        assertNotNull(stationRepositoryService.getByStationName(StationName.AIRPORT));
    }
}
