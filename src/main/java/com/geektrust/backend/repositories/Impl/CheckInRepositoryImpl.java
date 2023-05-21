package com.geektrust.backend.repositories.Impl;

import java.util.HashMap;
import java.util.Map;

import com.geektrust.backend.models.CheckIn;
import com.geektrust.backend.repositories.CheckInRepository;

public class CheckInRepositoryImpl implements CheckInRepository{
    private Map<String, CheckIn> checkInMap = new HashMap<>();

    @Override
    public void save(CheckIn checkIn){
        checkInMap.put(checkIn.getCardNumber(), checkIn);
    }

    @Override
    public CheckIn getByCardNumber(String cardNumber) {
        return checkInMap.get(cardNumber);
    }

    
}
