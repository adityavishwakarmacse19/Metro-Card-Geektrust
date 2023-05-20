package com.geektrust.backend.services;

import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.models.CheckIn;
import com.geektrust.backend.models.Station;

public interface CheckInService {
    void checkInPassenger(CheckIn checkIn);
    Station getStationByStationName(StationName stationName);
    boolean isCheckedIn(String cardNumber);
}
