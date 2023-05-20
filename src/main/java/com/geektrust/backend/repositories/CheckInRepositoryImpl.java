package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Map;

import com.geektrust.backend.models.CheckIn;

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
