package com.geektrust.backend.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.geektrust.backend.services.Impl.MetroCardServiceImpl;

public class MetroCardServiceTest {
    
    private final CardService metroCardService = new MetroCardServiceImpl();

    @Test
    public void testAddMetroCard() {
        metroCardService.balance("MC1", 101);
        Assertions.assertTrue(metroCardService.containsCard("MC1"));
        Assertions.assertTrue(metroCardService.containsCard("MC1"));
    }
}
