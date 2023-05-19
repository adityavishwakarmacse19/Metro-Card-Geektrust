package com.geektrust.backend.Repositories;

import com.geektrust.backend.dto.StationCollection;
import com.geektrust.backend.models.Station;
import com.geektrust.backend.models.Station.StationName;

public interface CollectionRepository {
    Station getByStationName(StationName stationName);
    Station addCollection(StationCollection stationCollection);
}
