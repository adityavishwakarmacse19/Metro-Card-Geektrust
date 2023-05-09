package com.geektrust.backend.Entities;

import com.geektrust.backend.DTO.EnumPassenger.Passenger;

public class MetroCard {
    String metroNumber;
    int balance;
    int cost;
    int discount = 0;
    String fromStation;    
    boolean ReturnJourney;
    Passenger passenger;

    public MetroCard() {}

    public String getFromStation() {
        return fromStation;
    }
    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }
    public Passenger getPassenger() {
        return passenger;
    }
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    public MetroCard(String metroNumber, int balance) {
        this.metroNumber = metroNumber;
        this.balance = balance;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }

   
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public boolean getReturnJourney() {
        return ReturnJourney;
    }
    public void setReturnJourney(boolean ReturnJourney) {
        this.ReturnJourney = ReturnJourney;
    }
    public String getMetroNumber() {
        return metroNumber;
    }
    public void setMetroNumber(String metroNumber) {
        this.metroNumber = metroNumber;
    }
   
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}