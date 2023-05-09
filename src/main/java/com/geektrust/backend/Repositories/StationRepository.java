package com.geektrust.backend.Repositories;

public interface StationRepository {
    StationDTO getByStation(String station);
    void save(StationDTO stationDTO);
}
