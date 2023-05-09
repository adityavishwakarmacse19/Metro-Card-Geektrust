package com.geektrust.backend.Service;

import com.geektrust.backend.Entities.MetroCard;
import com.geektrust.backend.Repositories.MetroCardRepository;
import com.geektrust.backend.Repositories.MetroCardRepositoryImpl;
import com.geektrust.backend.DTO.EnumPassenger.Passenger;

public class MetroServiceImpl implements MetroService{
    MetroCardRepository metroCardRepository = new MetroCardRepositoryImpl();
    StationService stationService = new StationServiceImpl();

    @Override
    public void balance(String metroNumber, int balance){
        MetroCard metroCard = new MetroCard(metroNumber, balance);
        metroCardRepository.save(metroCard);
    }

    @Override
    public void checkIn(String metroNumber, String passenger, String fromStation){

        if(metroCardRepository.containsMetroNumber(metroNumber)){

            MetroCard metroCard = metroCardRepository.getByMetroNumber(metroNumber);

            int cost = 0;
            int discount = 0;

            if(passenger.equalsIgnoreCase("adult")){
                cost = 200;
            } else if(passenger.equalsIgnoreCase("senior_citizen")){
                cost = 100;
            } else cost = 50; 

            String previousfromStation = metroCard.getFromStation();

            //done right
            if(previousfromStation!=null && previousfromStation != fromStation){
                boolean returnJourney = metroCard.getReturnJourney();
                returnJourney = !returnJourney;
                metroCard.setReturnJourney(returnJourney);
                if(returnJourney){
                    cost /= 2;
                    discount = cost;
                    
                }
            }
            metroCard.setDiscount(discount);

            int balance = metroCard.getBalance();
            if(cost > metroCard.getBalance()){
                int sufficientAmtToLoad = cost - metroCard.getBalance();
                cost += sufficientAmtToLoad * 2 / 100;
                balance = 0;
            } else {
                balance = metroCard.getBalance() - cost;
            }
            metroCard.setCost(cost);
            metroCard.setBalance(balance);

            //checkin done
            Passenger enumPassenger = stringToEnumPassenger(passenger);
            metroCard.setPassenger(enumPassenger);

            //done right
            metroCard.setFromStation(fromStation);

            metroCardRepository.save(metroCard);
            stationService.collect(metroCard);
        } 

    }

    @Override
    public StationService getStationService(){
        return stationService;
    }
    Passenger stringToEnumPassenger(String passenger){
        Passenger enumPassenger;
        if(passenger.equalsIgnoreCase("ADULT")){
            enumPassenger = Passenger.ADULT;
        } else if(passenger.equalsIgnoreCase("SENIOR_CITIZEN")){
            enumPassenger = Passenger.SENIOR_CITIZEN;
        } else {
            enumPassenger = Passenger.KID;
        }
        return enumPassenger;
    }
}
