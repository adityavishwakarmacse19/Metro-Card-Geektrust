package com.geektrust.backend.services;

import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.models.CheckIn;
import com.geektrust.backend.models.MetroCard;

public class CheckInServiceImpl implements CheckInService{

    CardService metroCardService;

    public CheckInServiceImpl(CardService metroCardService) {
        this.metroCardService = metroCardService;
    }

    @Override
    public void checkInPassenger(CheckIn checkIn) {
        String cardNumber = checkIn.getCardNumber();
        Passenger passenger = checkIn.getPassenger();
        StationName fromStation = checkIn.getFromStation()


        if(cardRepository.containsCardNumber(cardNumber)){

            MetroCard card = cardRepository.getByCardNumber(cardNumber);
            //calculate cost
            int cost = Passenger.valueOf(passenger).getCost();

            StationName previousFromStation = card.getFromStation();
            

            boolean returnJourney = card.getReturnJourney();

            //calculating discount
            int discount = 0;

            //whether retrun journey or not
            StationName currentFromStation = StationName.valueOf(fromStation);
            if(previousFromStation!=null && previousFromStation != currentFromStation){
                //setting current returnJourney
                returnJourney = !returnJourney;
                card.setReturnJourney(returnJourney);
                if(returnJourney){
                    cost /= Common.TWO;
                    discount = cost;
                }
            } 
    
            //CalculatingCostSurchargeAndBalance
            int balance = card.getBalance();
            if(cost > card.getBalance()){
                int sufficientAmtToLoad = cost - card.getBalance();
                cost += sufficientAmtToLoad * Common.TWO/ 100;
                balance = Common.ZERO;
            } else {
                balance = card.getBalance() - cost;
            }
            card.setBalance(balance);

            //checkin done

            //set given values
            card.setFromStation(currentFromStation);

            cardRepository.save(card);

            StationCollection stationCollection = new StationCollection(currentFromStation, discount, cost, Passenger.valueOf(passenger));
            stationRepositoryService.addCollection(stationCollection);
        }

    }
    }
    
}
