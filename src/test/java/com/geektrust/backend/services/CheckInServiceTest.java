package com.geektrust.backend.services;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.models.CheckIn;
import com.geektrust.backend.services.Impl.CheckInServiceImpl;
import com.geektrust.backend.services.Impl.MetroCardServiceImpl;

@TestInstance(Lifecycle.PER_CLASS)
public class CheckInServiceTest {

    private final static CardService metroCardService = new MetroCardServiceImpl();
    private final CheckInService checkInService = new CheckInServiceImpl(metroCardService);

    @BeforeAll
    public void setup() {
        metroCardService.balance("MC1", 101);
        metroCardService.balance("MC2", 501);
        metroCardService.balance("MC3", 1001);
    }

    @Order(1)
    @Test
    public void testAdultCheckIn() {
        CheckIn checkIn = new CheckIn("MC3", Passenger.ADULT, StationName.CENTRAL);
        checkInService.checkInPassenger(checkIn);
        assertNotEquals(0, checkInService.getStationByStationName(StationName.CENTRAL));
    }

    @Order(2)
    @Test
    public void testSeniorCheckIn() {
        CheckIn checkIn = new CheckIn("MC2", Passenger.SENIOR_CITIZEN, StationName.CENTRAL);
        checkInService.checkInPassenger(checkIn);

        assertNotEquals(0, checkInService.getStationByStationName(StationName.CENTRAL));
    }

    @Order(3)
    @Test
    public void testKidCheckIn() {
        CheckIn checkIn = new CheckIn("MC1", Passenger.KID, StationName.CENTRAL);
        checkInService.checkInPassenger(checkIn);

        assertNotNull(checkInService.getStationByStationName(StationName.CENTRAL));
    }

    @Order(4)
    @Test
    public void testAdultCheckInReturn() {
        CheckIn checkIn = new CheckIn("MC3", Passenger.ADULT, StationName.CENTRAL);
        checkInService.checkInPassenger(checkIn);
        assertNotNull(checkInService.isCheckedIn("MC3"));
        assertNotNull(checkInService.getStationByStationName(StationName.CENTRAL));
    }

    @Order(5)
    @Test
    public void testAdultCheckInReturnAgain() {
        CheckIn checkIn = new CheckIn("MC3", Passenger.ADULT, StationName.CENTRAL);
        checkInService.checkInPassenger(checkIn);
        CheckIn checkInReturn = new CheckIn("MC3", Passenger.ADULT, StationName.AIRPORT);
        checkInService.checkInPassenger(checkInReturn);

        assertTrue(checkInReturn.isReturnJourney());
        assertNotNull(checkInService.getStationByStationName(StationName.CENTRAL));
    }
}
