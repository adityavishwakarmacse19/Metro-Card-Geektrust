package com.geektrust.backend.models;

import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.constants.StationName;

public class CheckIn {
    String cardNumber;
    Passenger passenger;
    StationName fromStation;

    public CheckIn(String cardNumber, Passenger passenger, StationName fromStation) {
        this.cardNumber = cardNumber;
        this.passenger = passenger;
        this.fromStation = fromStation;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public Passenger getPassenger() {
        return passenger;
    }
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    public StationName getFromStation() {
        return fromStation;
    }
    public void setFromStation(StationName fromStation) {
        this.fromStation = fromStation;
    }
}
