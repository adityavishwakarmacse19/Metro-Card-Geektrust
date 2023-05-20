package com.geektrust.backend.repositories;

import com.geektrust.backend.models.MetroCard;

public interface CardRepository {
    void save(MetroCard card);
    MetroCard getByCardNumber(String cardNumber);
    boolean containsCardNumber(String cardNumber);
    void delete(MetroCard card);
}
