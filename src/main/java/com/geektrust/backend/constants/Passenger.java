package com.geektrust.backend.constants;

public enum Passenger{
    ADULT(200), KID(50), SENIOR_CITIZEN(100);

    private int fair;

    public int getFair(){
        return this.fair;
    }

    private Passenger(int fair){
        this.fair = fair;
    }
}