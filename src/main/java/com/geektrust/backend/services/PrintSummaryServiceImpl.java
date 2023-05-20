package com.geektrust.backend.services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.models.Station;

public class PrintSummaryServiceImpl implements PrintSummaryService{

    private CheckInService checkInService;
    public PrintSummaryServiceImpl(CheckInService checkInService){
        this.checkInService = checkInService;
    }

    @Override
    public void printSummary() {
        StationName[] stationNames = StationName.values();
        for(StationName stationName : stationNames){
            Station station = checkInService.getStationByStationName(stationName);
            if(station != null){
                Map<Passenger, Integer> passengerMap = station.getPassengerMap();
                //method to sort values  
                HashMap<Passenger, Integer> passengerMapSorted = sortValues(passengerMap); 
                //put the sorted passengerMap in station.getPassengerMap()
                station.setPassengerMap(passengerMapSorted);
                
                station.printStation();
            }
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
