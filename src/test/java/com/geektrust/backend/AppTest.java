package com.geektrust.backend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("App Test")
class AppTest {

    @Test
    public void Application_Test() throws Exception{
        // Arrange
        // Act   
        // Assert
        Assertions.assertTrue(true);
    }

    @Test
    void demoTestMethod() {
        assertTrue(true);
    }

}
