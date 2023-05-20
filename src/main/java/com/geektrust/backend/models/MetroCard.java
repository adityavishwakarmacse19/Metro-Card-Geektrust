package com.geektrust.backend.models;

import com.geektrust.backend.constants.StationName;

public class MetroCard {
    private String cardNumber;
    private int balance;

    public MetroCard(String metroNumber, int balance) {
        this.cardNumber = metroNumber;
        this.balance = balance;
    }

    public String getCardNumber() {
		return cardNumber;
	}

   
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}