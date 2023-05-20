package com.geektrust.backend.models;

import com.geektrust.backend.constants.Common;
import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.dto.Amounts;

public class CheckIn {
    private String cardNumber;
    private Passenger passenger;
    private StationName fromStation;
    private boolean returnJourney;
    private Amounts amounts;

    public CheckIn(String cardNumber, Passenger passenger, StationName fromStation) {
        this.cardNumber = cardNumber;
        this.passenger = passenger;
        this.fromStation = fromStation;
        this.returnJourney = false;
        this.amounts = new Amounts(0, 0);
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
                discount = passenger.getFair()/Common.TWO;
            }
        } else {
            // checkin is not return journey
            // setting current returnJourney
            this.returnJourney = false;
            discount = Common.ZERO;
        }
        return discount;
    }

    public Amounts getAmounts() {
        return amounts;
    }

    public void setAmounts(Amounts amounts) {
        this.amounts = amounts;
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
