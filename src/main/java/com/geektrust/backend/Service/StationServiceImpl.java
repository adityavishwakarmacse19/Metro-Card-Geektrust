package com.geektrust.backend.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.geektrust.backend.Entities.MetroCard;
import com.geektrust.backend.DTO.EnumPassenger.Passenger;
import com.geektrust.backend.Constants.Common;
import com.geektrust.backend.DTO.StationDTO;
import com.geektrust.backend.Repositories.StationRepository;
import com.geektrust.backend.Repositories.StationRepositoryImpl;

public class StationServiceImpl implements StationService{
    StationRepository stationRepository = new StationRepositoryImpl();

    public void collect(MetroCard metroCard){
        String station = metroCard.getFromStation();
        int balance = metroCard.getBalance();
        int cost = metroCard.getCost();
        int discount = metroCard.getDiscount();
        String metroNumber =  metroCard.getMetroNumber();
        boolean returnJourney = metroCard.getReturnJourney();
        Passenger passenger = metroCard.getPassenger();

        StationDTO stationDTO = stationRepository.getByStation(station);
        if(stationDTO != null){
            int totalCharges = stationDTO.getTotalCharges() + cost;
            stationDTO.setTotalCharges(totalCharges);

            int totalDiscount = stationDTO.getTotalDiscount() + discount;
            stationDTO.setTotalDiscount(totalDiscount);

            Map<Passenger, Integer> passengerMap = stationDTO.getPassengerMap();
            int passengerCount = Common.ONE;
            if(passengerMap.containsKey(passenger)){
                passengerCount = passengerMap.get(passenger) + Common.ONE;
            }

            passengerMap.put(passenger, passengerCount);
            stationDTO.setPassengerMap(passengerMap);

        } else{
            Map<Passenger, Integer> passengerMap = new HashMap<>();
            passengerMap.put(passenger, Common.ONE);
            stationDTO = new StationDTO(station, cost, discount, passengerMap);
        }

        stationRepository.save(stationDTO);
        /*
            int totalCharges;
            int totalDiscount;
            Map<Passenger, Integer> enumMap;
        */
    }

    public void print_summary(){

        String[] stations = {"CENTRAL", "AIRPORT"};

        for(String station : stations){
            StationDTO stationDTO = stationRepository.getByStation(station);

            int totalCharges = stationDTO.getTotalCharges();
            int totalDiscount = stationDTO.getTotalDiscount();

            System.out.println("TOTAL_COLLECTION " + station + " " + totalCharges + " " + totalDiscount);
            System.out.println("PASSENGER_TYPE_SUMMARY");

            Map<Passenger, Integer> passengerMap = stationDTO.getPassengerMap();
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
