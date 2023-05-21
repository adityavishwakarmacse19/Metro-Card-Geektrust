package com.geektrust.backend.repositories.Impl;

import java.util.HashMap;
import java.util.Map;

import com.geektrust.backend.models.Journey;
import com.geektrust.backend.repositories.JourneyRepository;

public class JourneyRepositoryImpl implements JourneyRepository{
    private Map<String, Journey> journeyMap = new HashMap<>();

    @Override
    public void save(Journey journey){
        journeyMap.put(journey.getCardNumber(), journey);
    }

    @Override
    public Journey getByCardNumber(String cardNumber) {
        return journeyMap.get(cardNumber);
    }
    
}
