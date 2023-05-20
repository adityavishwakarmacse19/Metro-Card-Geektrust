package com.geektrust.backend.models;

import com.geektrust.backend.constants.Common;
import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.constants.StationName;

public class CheckIn {
    private String cardNumber;
    private Passenger passenger;
    private StationName fromStation;
    private boolean returnJourney; 

    public CheckIn(String cardNumber, Passenger passenger, StationName fromStation) {
        this.cardNumber = cardNumber;
        this.passenger = passenger;
        this.fromStation = fromStation;
        this.returnJourney = false;
    }

    public int calcuLateDiscount(StationName previousFromStation, boolean previousReturnjourney){
        int discount = Common.ZERO;
        // whether checkn is a retrun journey or not
        if(previousFromStation!=null && previousFromStation != fromStation){
            // checkin is return journey
            // setting current returnJourney
            boolean  returnJourney = !previousReturnjourney;
            this.returnJourney = returnJourney;

            if(returnJourney){
                discount = passenger.getCost()/Common.TWO;
            }
        } else {
            // checkin is not return journey
            // setting current returnJourney
            this.returnJourney = false;
            discount = Common.ZERO;
        }
        return discount;
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
    public boolean isReturnJourney() {
        return returnJourney;
    }
    public void setReturnJourney(boolean returnJourney) {
        this.returnJourney = returnJourney;
    }
}
