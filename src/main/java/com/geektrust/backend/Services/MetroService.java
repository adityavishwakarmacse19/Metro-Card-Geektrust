package com.geektrust.backend.Services;

public interface MetroService {
    void balance(String metroNumber, int balance);
    void checkIn(String metroNumber, String passenger, String fromStation);
    void print_summary();
}