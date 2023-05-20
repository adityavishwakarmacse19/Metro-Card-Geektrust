package com.geektrust.backend.services;

import java.util.List;

import com.geektrust.backend.models.MetroCard;

public interface CardService {
    //save card from paramters in the map
    void balance(String cardNumber, int balance);

    // //return all the cards
    // List<MetroCard> getCards();

    // use from parmaters card and return amount left to pay 
    int useCard(String cardNumber, int amount);
}
