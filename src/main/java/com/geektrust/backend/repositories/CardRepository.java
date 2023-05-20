package com.geektrust.backend.repositories;

import com.geektrust.backend.models.Card;

public interface CardRepository {
    Card getByCardNumber(String cardNumber);
    boolean containsCardNumber(String cardNumber);
    void save(Card card);
    void delete(Card card);
}
