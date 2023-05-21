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

    public int calcuLateDiscount(){
        // int discount = Common.ZERO;
        // // whether journey is a retrun journey or not
        // if(previousFromStation!=null && previousFromStation != fromStation){
        //     // journey is return journey
        //     // setting current isReturnJourney
        //     boolean isReturnJourney = !previousIsReturnjourney;
        this.isReturnJourney = true;

            // if(isReturnJourney){
                // discount = passenger.getFair()/Common.TWO;
            // }
        // } else {
        //     // journey is not return journey
        //     // setting current returnJourney
        //     this.isReturnJourney = false;
        //     discount = Common.ZERO;
        // }
        return passenger.getFair()/Common.TWO;
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
