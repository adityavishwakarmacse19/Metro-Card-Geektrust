package com.geektrust.backend.dto;

import com.geektrust.backend.constants.Passenger;

public class PassengerCount {
    private int count;
    private Passenger passenger;

    public PassengerCount(int count, Passenger passenger) {
        this.count = count;
        this.passenger = passenger;
    }

    public int getCount() {
        return count;
    }    
    
    public Passenger getPassenger() {
        return passenger;
    }
}
