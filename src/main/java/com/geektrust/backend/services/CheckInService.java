package com.geektrust.backend.services;

import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.models.Journey;
import com.geektrust.backend.models.Station;

public interface CheckInService {
    void checkInPassenger(Journey journey);
    Station getStationByStationName(StationName stationName);
    boolean isCheckedIn(String cardNumber);
}
