package com.geektrust.backend.services;

import com.geektrust.backend.models.MetroCard;

public interface CardService {
    //save card from paramters in the map
    void balance(String cardNumber, int balance);
    
    // use from parmaters card and return amount left to pay 
    int useCard(String cardNumber, int cost);

    MetroCard getCardByCardNumber(String cardNumber);
}
