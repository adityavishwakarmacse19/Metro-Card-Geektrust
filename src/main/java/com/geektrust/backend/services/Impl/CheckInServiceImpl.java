package com.geektrust.backend.services.Impl;

import com.geektrust.backend.constants.Common;
import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.dto.StationCollection;
import com.geektrust.backend.models.CheckIn;
import com.geektrust.backend.models.MetroCard;
import com.geektrust.backend.models.Station;
import com.geektrust.backend.repositories.CheckInRepository;
import com.geektrust.backend.repositories.StationRepository;
import com.geektrust.backend.repositories.Impl.CheckInRepositoryImpl;
import com.geektrust.backend.repositories.Impl.StationRepositoryImpl;
import com.geektrust.backend.services.CardService;
import com.geektrust.backend.services.CheckInService;


public class CheckInServiceImpl implements CheckInService{

    private CardService metroCardService;
    private CheckInRepository checkInRepository = new CheckInRepositoryImpl();
    private StationRepository stationRepository = new StationRepositoryImpl();


    public CheckInServiceImpl(CardService metroCardService) {
        this.metroCardService = metroCardService;
    }

    @Override
    public void checkInPassenger(CheckIn checkIn) {
        String cardNumber = checkIn.getCardNumber();
        Passenger passenger = checkIn.getPassenger();
        StationName fromStation = checkIn.getFromStation();

        MetroCard card = metroCardService.getCardByCardNumber(cardNumber);
        if(card != null){
            CheckIn previousCheckIn = checkInRepository.getByCardNumber(cardNumber);

            //calculating discount
            int discount = Common.ZERO;
            if(previousCheckIn != null){
                StationName previousFromStation = previousCheckIn.getFromStation(); 
                boolean previousReturnjourney = previousCheckIn.isReturnJourney();
                discount = checkIn.calcuLateDiscount(previousFromStation, previousReturnjourney);
            }

            //calculate cost
            int cost = passenger.getFair() - discount;
            int remaingCost = metroCardService.useCard(cardNumber, cost);
            cost += remaingCost * Common.SURCHARGE;

            //save the current checkIn
            checkInRepository.save(checkIn);

            //add the current collection to  stationRepository
            StationCollection stationCollection = new StationCollection(fromStation, discount, cost, passenger);
            stationRepository.addCollection(stationCollection);
        }
    }

    @Override
    public boolean isCheckedIn(String cardNumber){
        return checkInRepository.getByCardNumber(cardNumber) != null;
    }

    @Override
    public Station getStationByStationName(StationName stationName){
       return  stationRepository.getByStationName(stationName);
    }
     
}
