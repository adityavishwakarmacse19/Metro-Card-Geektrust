package com.geektrust.backend.constants;

public enum Passenger{
    ADULT(200), KID(50), SENIOR_CITIZEN(100);

    private int cost;

    public int getCost(){
        return this.cost;
    }

    private Passenger(int cost){
        this.cost = cost;
    }
}