package com.geektrust.backend.Services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.geektrust.backend.Repositories.CardRepository;
import com.geektrust.backend.Repositories.CardRepositoryImpl;
import com.geektrust.backend.Repositories.CollectionRepository;
import com.geektrust.backend.Repositories.CollectionRepositoryImpl;
import com.geektrust.backend.constants.Common;
import com.geektrust.backend.dto.StationCollection;
import com.geektrust.backend.models.Card;
import com.geektrust.backend.models.Station;
import com.geektrust.backend.models.Station.StationName;

public class MetroServiceImpl implements MetroService{
    public enum Passenger{
        ADULT(200), KID(50), SENIOR_CITIZEN(100);

        private int cost;

        public int getCost(){
            return this.cost;
        }

        private Passenger(int cost){
            this.cost = cost;
        }
    }

    CardRepository cardRepository = new CardRepositoryImpl();
    CollectionRepository collectionRepository = new CollectionRepositoryImpl();

    @Override
    public void balance(String cardNumber, int balance){
        Card card = new Card(cardNumber, balance);
        cardRepository.save(card);
    }  

    @Override
    public void checkIn(String cardNumber, String passenger, String fromStation){

        if(cardRepository.containsCardNumber(cardNumber)){

            Card card = cardRepository.getByCardNumber(cardNumber);
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
            collectionRepository.addCollection(stationCollection);

        }

    }

    @Override
    public void print_summary(){

        
        StationName[] stationNames = StationName.values();
    
        for(StationName stationName : stationNames){
                // StationDTO stationDTO = stationRepository.getByStation(station);
                Station station = collectionRepository.getByStationName(stationName);
    
                int totalCharges = station.getTotalCharges();
                int totalDiscount = station.getTotalDiscount();
    
                System.out.println("TOTAL_COLLECTION " + station.getStationName() + " " + totalCharges + " " + totalDiscount);
                System.out.println("PASSENGER_TYPE_SUMMARY");
    
                Map<Passenger, Integer> passengerMap = station.getPassengerMap();
                // = stationDTO.getPassengerMap();
                //method to sort values  
                HashMap<Passenger, Integer> passengerMapSorted = sortValues(passengerMap);
      
                for(Entry<Passenger, Integer> entry : passengerMapSorted.entrySet()){
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }
            }
            
            
    }
    
    private static HashMap<Passenger, Integer> sortValues(Map<Passenger, Integer> passengerMap)   
    {   
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
