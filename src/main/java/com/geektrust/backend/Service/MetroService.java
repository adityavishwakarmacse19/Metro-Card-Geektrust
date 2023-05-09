package com.geektrust.backend.Service;

public interface MetroService {
    void balance(String metroNumber, int balance);
    void checkIn(String metroNumber, String passenger, String fromStation);
    StationService getStationService();
}