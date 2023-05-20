package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Map;

import com.geektrust.backend.models.MetroCard;

public class CardRepositoryImpl implements CardRepository{
    private Map<String, MetroCard> CardMap = new HashMap<>();

    @Override
    public void save(MetroCard card) {
        CardMap.put(card.getCardNumber(),card);
    }

    @Override
    public void delete(MetroCard card) {
        CardMap.remove(card.getCardNumber());
    }

    @Override
    public boolean containsCardNumber(String cardNumber) {
        return CardMap.containsKey(cardNumber);
    }

    @Override
    public MetroCard getByCardNumber(String cardNumber) {
        return CardMap.get(cardNumber);
    }

    
}
