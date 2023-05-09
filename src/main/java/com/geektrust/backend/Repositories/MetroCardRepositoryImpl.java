package com.geektrust.backend.Repositories;

import java.util.HashMap;
import java.util.Map;
import com.geektrust.backend.Entities.MetroCard;

public class MetroCardRepositoryImpl implements MetroCardRepository{
    Map<String, MetroCard> metroCardMap = new HashMap<>();

    @Override
    public MetroCard save(MetroCard metroCard) {
        return metroCardMap.put(metroCard.getMetroNumber(), metroCard);
    }

    @Override
    public void delete(MetroCard metroCard) {
        metroCardMap.remove(metroCard.getMetroNumber());
    }

    @Override
    public boolean containsMetroNumber(String metroNumber) {
        return metroCardMap.containsKey(metroNumber);
    }

    @Override
    public MetroCard getByMetroNumber(String metroNumber) {
        return metroCardMap.get(metroNumber);
    }
    
}
