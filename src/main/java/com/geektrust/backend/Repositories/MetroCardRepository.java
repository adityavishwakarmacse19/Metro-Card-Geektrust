package com.geektrust.backend.Repositories;

import com.geektrust.backend.Entities.MetroCard;

public interface MetroCardRepository {
    MetroCard getByMetroNumber(String metroNumber);
    boolean containsMetroNumber(String metroNumber);
    MetroCard save(MetroCard metroCard);
    void delete(MetroCard metroCard);
}
