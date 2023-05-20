package com.geektrust.backend.dto;

import com.geektrust.backend.constants.Passenger;

public class PassengerCount {
    private Passenger passenger;
    private int count;
    

    public PassengerCount(Passenger passenger, int count) {
        this.passenger = passenger;
        this.count = count;
    }

    public Passenger getPassenger() {
        return passenger;
    }    
    
    public int getCount() {
        return count;
    }  
}
