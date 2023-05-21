package com.geektrust.backend.services.Impl;

import com.geektrust.backend.constants.Common;
import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.dto.StationCollection;
import com.geektrust.backend.models.Journey;
import com.geektrust.backend.models.MetroCard;
import com.geektrust.backend.models.Station;
import com.geektrust.backend.repositories.JourneyRepository;
import com.geektrust.backend.repositories.StationRepository;
import com.geektrust.backend.repositories.Impl.JourneyRepositoryImpl;
import com.geektrust.backend.repositories.Impl.StationRepositoryImpl;
import com.geektrust.backend.services.CardService;
import com.geektrust.backend.services.CheckInService;


public class CheckInServiceImpl implements CheckInService{

    private CardService metroCardService;
    private JourneyRepository journeyRepository = new JourneyRepositoryImpl();
    private StationRepository stationRepository = new StationRepositoryImpl();


    public CheckInServiceImpl(CardService metroCardService) {
        this.metroCardService = metroCardService;
    }

    @Override
    public void checkInPassenger(Journey journey) {
        String cardNumber = journey.getCardNumber();
        Passenger passenger = journey.getPassenger();
        StationName fromStation = journey.getFromStation();

        MetroCard card = metroCardService.getCardByCardNumber(cardNumber);
        if(card != null){
            Journey previousJourney = journeyRepository.getByCardNumber(cardNumber);

            //calculating discount
            int discount = Common.ZERO;
            // whether journey is a retrun journey or not
            boolean isReturnJourney = previousJourney != null && previousJourney.getFromStation() != null && previousJourney.getFromStation() != fromStation && !previousJourney.isReturnJourney() ;
            if(isReturnJourney){
                discount = journey.calcuLateDiscount();
            }

            //calculate cost
            int cost = passenger.getFair() - discount;
            int remaingCost = metroCardService.useCard(cardNumber, cost);
            cost += remaingCost * Common.SURCHARGE;

            //save the current journey
            journeyRepository.save(journey);

            //add the current collection to  stationRepository
            StationCollection stationCollection = new StationCollection(fromStation, discount, cost, passenger);
            stationRepository.addCollection(stationCollection);
        }
    }

    @Override
    public boolean isCheckedIn(String cardNumber){
        return journeyRepository.getByCardNumber(cardNumber) != null;
    }

    @Override
    public Station getStationByStationName(StationName stationName){
       return  stationRepository.getByStationName(stationName);
    }
     
}
