package com.geektrust.backend.services.Impl;

import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.models.Station;
import com.geektrust.backend.services.CheckInService;
import com.geektrust.backend.services.PrintSummaryService;

public class PrintSummaryServiceImpl implements PrintSummaryService{

    private CheckInService checkInService;
    public PrintSummaryServiceImpl(CheckInService checkInService){
        this.checkInService = checkInService;
    }

    @Override
    public void printSummary() {
        StationName[] stationNames = StationName.values();
        for(StationName stationName : stationNames){
            Station station = checkInService.getStationByStationName(stationName);
            if(station != null){
                //method to sort passengerMap  
                station.sortPassengerMap();
                station.printStation();
            }
        }  
    }
    
}
