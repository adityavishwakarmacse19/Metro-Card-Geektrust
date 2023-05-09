package com.geektrust.backend.Repositories;

import java.util.HashMap;
import java.util.Map;


public class StationRepositoryImpl implements StationRepository{
    Map<String, StationDTO> map = new HashMap<>();

    public StationDTO getByStation(String station){
        return map.get(station);
    }


    public void save(StationDTO stationDTO){
        map.put(stationDTO.getStation(), stationDTO);
    }
}
