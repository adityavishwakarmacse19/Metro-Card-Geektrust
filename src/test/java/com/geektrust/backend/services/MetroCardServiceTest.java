package com.geektrust.backend.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.geektrust.backend.models.MetroCard;

public class MetroCardServiceTest {
    
    private final CardService metroCardService = new MetroCardServiceImpl();

    @Test
    public void testAddMetroCard() {
        MetroCard metroCard = new MetroCard("MC1", 101);
        metroCardService.balance("MC1", 101);
        Assertions.assertEquals(metroCardService.getCardByCardNumber("MC1").getBalance(), metroCard.getBalance());
        Assertions.assertEquals(metroCardService.getCardByCardNumber("MC1").getCardNumber(), metroCard.getCardNumber());
    }
}
