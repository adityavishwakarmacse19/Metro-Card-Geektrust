package com.geektrust.backend.Repositories;

import java.util.HashMap;
import java.util.Map;

import com.geektrust.backend.models.Card;

public class CardRepositoryImpl implements CardRepository{
    Map<String, Card> CardMap = new HashMap<>();

    @Override
    public Card save(Card Card) {
        return CardMap.put(Card.getCardNumber(),Card);
    }

    @Override
    public void delete(Card metroCard) {
        CardMap.remove(metroCard.getCardNumber());
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
