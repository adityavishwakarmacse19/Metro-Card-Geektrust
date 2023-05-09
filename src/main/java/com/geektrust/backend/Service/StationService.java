package com.geektrust.backend.Service;

import com.geektrust.backend.Entities.MetroCard;

public interface StationService {
    void collect(MetroCard metroCard);

    void print_summary();
}
