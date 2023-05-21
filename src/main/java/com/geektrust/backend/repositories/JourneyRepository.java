package com.geektrust.backend.repositories;

import com.geektrust.backend.models.Journey;

public interface JourneyRepository {
    void save(Journey journey);
    Journey getByCardNumber(String cardNumber);
}
