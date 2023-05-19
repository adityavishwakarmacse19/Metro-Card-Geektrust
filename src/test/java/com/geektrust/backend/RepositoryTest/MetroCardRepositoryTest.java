package com.geektrust.backend.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.geektrust.backend.Repositories.CardRepository;
import com.geektrust.backend.Repositories.CardRepositoryImpl;
import com.geektrust.backend.models.Card;

@DisplayName("App Test")
class MetroCardRepositoryTest {

    CardRepository cardRepository;
    @BeforeEach                                         
    void setUp() {
        cardRepository = new CardRepositoryImpl();
    }

    @Test
    public void saveTest() throws Exception{
        // Arrange
        // Act   
        // Assert
        Card card = new Card("AB1", 100);
        cardRepository.save(card);
        assertEquals(cardRepository.getByCardNumber("AB1"), card);
    }

    @Test
    void demoTestMethod() {
        assertTrue(false);
    }

}
