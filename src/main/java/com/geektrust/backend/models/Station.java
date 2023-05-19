package com.geektrust.backend.models;

import java.util.Map;

import com.geektrust.backend.Services.MetroServiceImpl.Passenger;

public class Station {
    StationName stationName;   
    int totalDiscount;
    int totalCharges;
    Map<Passenger, Integer> passengerMap;

	public Station(StationName stationName, int totalDiscount, int totalCharges, Map<Passenger, Integer> passengerMap) {
        this.stationName = stationName;
        this.totalDiscount = totalDiscount;
        this.totalCharges = totalCharges;
        this.passengerMap = passengerMap;
    }

    public Map<Passenger, Integer> getPassengerMap() {
		return passengerMap;
	}

	public void setPassengerMap(Map<Passenger, Integer> passengerMap) {
		this.passengerMap = passengerMap;
	}

	public StationName getStationName() {
		return stationName;
	}

    public int getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(int totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	
	public int getTotalCharges() {
		return totalCharges;
	}

	public void setTotalCharges(int totalCharges) {
		this.totalCharges = totalCharges;
	}

	public enum StationName{
        CENTRAL, AIRPORT
    }

}
