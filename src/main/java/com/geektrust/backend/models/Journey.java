package com.geektrust.backend.models;

import com.geektrust.backend.constants.Common;
import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.constants.StationName;

public class Journey {
    private String cardNumber;
    private Passenger passenger;
    private StationName fromStation;
    private boolean isReturnJourney;

    public Journey(String cardNumber, Passenger passenger, StationName fromStation) {
        this.cardNumber = cardNumber;
        this.passenger = passenger;
        this.fromStation = fromStation;
        this.isReturnJourney = false;
    }
    // whether journey is a retrun journey or not
    public void calculateIsReturnJourney(Journey previousJourney){
        if(previousJourney != null){
            isReturnJourney = previousJourney.getFromStation() != null && previousJourney.getFromStation() != fromStation && !previousJourney.isReturnJourney();
        }
    }

    public int calcuLateDiscount(){
        // int discount = Common.ZERO;
        // // whether journey is a retrun journey or not
        // if(previousFromStation!=null && previousFromStation != fromStation){
        //     // journey is return journey
        //     // setting current isReturnJourney
        //     boolean isReturnJourney = !previousIsReturnjourney;

            // if(isReturnJourney){
                // discount = passenger.getFair()/Common.TWO;
            // }
        // } else {
        //     // journey is not return journey
        //     // setting current returnJourney
        //     this.isReturnJourney = false;
        //     discount = Common.ZERO;
        // }
        if(isReturnJourney)
        return passenger.getFair()/Common.TWO;
        else return Common.ZERO;
    }

    
    public String getCardNumber() {
        return cardNumber;
    }
    // public void setCardNumber(String cardNumber) {
    //     this.cardNumber = cardNumber;
    // }
    public Passenger getPassenger() {
        return passenger;
    }
    // public void setPassenger(Passenger passenger) {
    //     this.passenger = passenger;
    // }
    public StationName getFromStation() {
        return fromStation;
    }
    // public void setFromStation(StationName fromStation) {
    //     this.fromStation = fromStation;
    // }
    public boolean isReturnJourney() {
        return isReturnJourney;
    }
    // public void setReturnJourney(boolean returnJourney) {
    //     this.returnJourney = returnJourney;
    // }
}
