package com.geektrust.backend.dto;

public class Amounts {
    private int cost;
    private int discount;


    public Amounts(int cost, int discount) {
        this.cost = cost;
        this.discount = discount;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDiscount() {
        return discount;
    }
        
    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
