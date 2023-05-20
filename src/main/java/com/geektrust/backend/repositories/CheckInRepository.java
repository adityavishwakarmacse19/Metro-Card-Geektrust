package com.geektrust.backend.repositories;

import com.geektrust.backend.models.CheckIn;

public interface CheckInRepository {
    void save(CheckIn checkIn);
    CheckIn getByCardNumber(String cardNumber);
}
