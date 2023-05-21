package com.geektrust.backend.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.models.CheckIn;
import com.geektrust.backend.services.Impl.CheckInServiceImpl;
import com.geektrust.backend.services.Impl.MetroCardServiceImpl;
import com.geektrust.backend.services.Impl.PrintSummaryServiceImpl;

@TestInstance(Lifecycle.PER_CLASS)
public class PrintSummaryServiceTest {
    private CardService metroCardService = new MetroCardServiceImpl();
    private CheckInService checkInService = new CheckInServiceImpl(metroCardService);
    private PrintSummaryService printSummaryService = new PrintSummaryServiceImpl(checkInService);

    @BeforeAll
    public void setup() {
        metroCardService.balance("MC1", 101);
        metroCardService.balance("MC2", 501);
        metroCardService.balance("MC3", 1001);
        checkInService.checkInPassenger(new CheckIn("MC3", Passenger.ADULT, StationName.CENTRAL));
    }

    @Test
    public void testPrintSummaryCall () {
        printSummaryService.printSummary();
    }
}
