package com.geektrust.backend.models;

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
import com.geektrust.backend.dto.StationCollection;

public class Station {
    private StationName stationName;   
    private int totalDiscount;
    private int totalCharges;
    private Map<Passenger, Integer> passengerMap;
	

	public Station(StationName stationName, int totalDiscount, int totalCharges, Map<Passenger, Integer> passengerMap) {
        this.stationName = stationName;
        this.totalDiscount = totalDiscount;
        this.totalCharges = totalCharges;
        this.passengerMap = passengerMap;
    }

	public void sortPassengerMap() {
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
		passengerMap = sortedHashMap;
        return;
    }

	public void printStation(){
		System.out.println("TOTAL_COLLECTION " + this.getStationName().name() + " " + this.getTotalCharges() + " " + this.getTotalDiscount());
		System.out.println("PASSENGER_TYPE_SUMMARY");
		for(Entry<Passenger, Integer> entry : this.getPassengerMap().entrySet()){
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

	public void collect(StationCollection stationCollection){
		//update totalDiscount of this 
		totalDiscount += stationCollection.getDiscount();

		//update totalCharges of this
		 totalCharges += stationCollection.getCost();

		//TODO: reduce some variables
		//update passengerMap of this 
		Passenger passenger = stationCollection.getPassenger();
		if(passengerMap.containsKey(passenger)){
			int passengerCount = passengerMap.get(passenger);
			passengerMap.put(passenger, passengerCount+1);
		} else {
			passengerMap.put(passenger, 1);
		}
	}

    public Map<Passenger, Integer> getPassengerMap() {
		return passengerMap;
	}

	// public void setPassengerMap(Map<Passenger, Integer> passengerMap) {
	// 	this.passengerMap = passengerMap;
	// }

	public StationName getStationName() {
		return stationName;
	}

    public int getTotalDiscount() {
		return totalDiscount;
	}

	// public void setTotalDiscount(int totalDiscount) {
	// 	this.totalDiscount = totalDiscount;
	// }
	
	public int getTotalCharges() {
		return totalCharges;
	}

	// public void setTotalCharges(int totalCharges) {
	// 	this.totalCharges = totalCharges;
	// }

}
