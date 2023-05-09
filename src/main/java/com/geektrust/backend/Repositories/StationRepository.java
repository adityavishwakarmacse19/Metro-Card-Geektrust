package com.geektrust.backend.Repositories;

import com.geektrust.backend.DTO.StationDTO;

public interface StationRepository {
    StationDTO getByStation(String station);
    void save(StationDTO stationDTO);
}
