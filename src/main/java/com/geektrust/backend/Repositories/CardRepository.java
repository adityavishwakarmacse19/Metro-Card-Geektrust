package com.geektrust.backend.Repositories;

import com.geektrust.backend.models.Card;

public interface CardRepository {
    Card getByCardNumber(String cardNumber);
    boolean containsCardNumber(String cardNumber);
    Card save(Card card);
    void delete(Card card);
}
