package com.geektrust.backend.dto;

import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.constants.StationName;


//TODO: change name
public class StationCollection {
    private StationName stationName;
	private int discount;
	private int cost;
	private Passenger passenger;


    public StationCollection(StationName stationName, int discount, int cost, Passenger passenger) {
		this.stationName = stationName;
		this.discount = discount;
		this.cost = cost;
		this.passenger = passenger;
	}

	public StationName getStationName() {
		return stationName;
	}

    public int getDiscount() {
		return discount;
	}

    public int getCost() {
		return cost;
	}

	public Passenger getPassenger() {
		return passenger;
	}

}
