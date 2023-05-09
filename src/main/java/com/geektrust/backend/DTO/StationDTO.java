package com.geektrust.backend.DTO;

import java.util.Map;
import com.geektrust.backend.DTO.EnumPassenger.Passenger;

public class StationDTO {
    String station;
    public StationDTO(String station, int totalCharges, int totalDiscount,
            Map<Passenger, Integer> passengerMap) {
        this.station = station;
        this.totalCharges = totalCharges;
        this.totalDiscount = totalDiscount;
        this.passengerMap = passengerMap;
    }
    int totalCharges;
    int totalDiscount;
    Map<Passenger, Integer> passengerMap;

    public Map<Passenger, Integer> getPassengerMap() {
        return passengerMap;
    }
    public void setPassengerMap(Map<Passenger, Integer> passengerMap) {
        this.passengerMap = passengerMap;
    }
    public String getStation() {
        return station;
    }
    public void setStation(String station) {
        this.station = station;
    }
    
    public int getTotalCharges() {
        return totalCharges;
    }
    public void setTotalCharges(int totalCharges) {
        this.totalCharges = totalCharges;
    }
    
    public int getTotalDiscount() {
        return totalDiscount;
    }
    public void setTotalDiscount(int totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
    

}
