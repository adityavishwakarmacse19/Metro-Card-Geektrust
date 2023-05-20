package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Map;

import com.geektrust.backend.models.Card;

public class CardRepositoryImpl implements CardRepository{
    Map<String, Card> CardMap = new HashMap<>();

    @Override
    public void save(Card card) {
        CardMap.put(card.getCardNumber(),card);
    }

    @Override
    public void delete(Card card) {
        CardMap.remove(card.getCardNumber());
    }

    @Override
    public boolean containsCardNumber(String cardNumber) {
        return CardMap.containsKey(cardNumber);
    }

    @Override
    public Card getByCardNumber(String cardNumber) {
        return CardMap.get(cardNumber);
    }

    
}
