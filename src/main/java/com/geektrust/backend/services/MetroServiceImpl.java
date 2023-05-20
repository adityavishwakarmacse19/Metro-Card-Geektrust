package com.geektrust.backend.services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.geektrust.backend.constants.Common;
import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.dto.StationCollection;
import com.geektrust.backend.models.MetroCard;
import com.geektrust.backend.models.Station;
import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.repositories.CardRepository;
import com.geektrust.backend.repositories.CardRepositoryImpl;
import com.geektrust.backend.repositoryServices.StationRepositoryService;
import com.geektrust.backend.repositoryServices.StationRepositoryServiceImpl;

public class MetroServiceImpl implements MetroService{


    CardRepository cardRepository = new CardRepositoryImpl();
    StationRepositoryService stationRepositoryService = new StationRepositoryServiceImpl();

    @Override
    public void balance(String cardNumber, int balance){
        MetroCard card = new MetroCard(cardNumber, balance);
        cardRepository.save(card);
    }  

    @Override
    public void checkIn(String cardNumber, String passenger, String fromStation){

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

    @Override
    public void print_summary(){        
        StationName[] stationNames = StationName.values();
        for(StationName stationName : stationNames){
            Station station = stationRepositoryService.getByStationName(stationName);
    
            Map<Passenger, Integer> passengerMap = station.getPassengerMap();
            //method to sort values  
            HashMap<Passenger, Integer> passengerMapSorted = sortValues(passengerMap); 
            //put the sorted passengerMap in station.getPassengerMap()
            station.setPassengerMap(passengerMapSorted);
            
            station.printStation();
        }            
    }
    
    private static HashMap<Passenger, Integer> sortValues(Map<Passenger, Integer> passengerMap) {
        List<Entry<Passenger, Integer>> list = new LinkedList<>(passengerMap.entrySet());  
    
        //Custom Comparator  
        list.sort(new Comparator()   
        {  
            @Override
            public int compare(Object o1, Object o2) {
                //ascending order
                if(((Entry<Passenger, Integer>)o2).getValue() == (((Entry<Passenger,Integer>)o1).getValue())){
                    return ((Entry<Passenger, Integer>)o1).getKey().compareTo(((Entry<Passenger,Integer>)o2).getKey());
                }
    
                //descending order
                return ((Entry<Passenger, Integer>)o2).getValue().compareTo(((Entry<Passenger,Integer>)o1).getValue());
            }
        });
            
        //copying the sorted list in HashMap to preserve the iteration order  
        HashMap<Passenger, Integer> sortedHashMap = new LinkedHashMap<>();  
        for (Iterator<Entry<Passenger, Integer>> it = list.iterator(); it.hasNext();)   
        {  
            Entry<Passenger, Integer> entry = it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());  
        }   
        return sortedHashMap;  
    }

}
