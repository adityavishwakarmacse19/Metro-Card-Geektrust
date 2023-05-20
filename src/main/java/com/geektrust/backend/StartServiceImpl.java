package com.geektrust.backend;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import com.geektrust.backend.constants.Common;
import com.geektrust.backend.services.MetroService;
import com.geektrust.backend.services.MetroCardServiceImpl;

public class StartServiceImpl implements StartService{

    @Override
    public void start(String[] args) throws IOException {
        MetroService metroService = new MetroCardServiceImpl();

        // Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned

            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
               String[] input = sc.nextLine().split(Common.SPACE, Common.TWO);
               switch (input[0]) {
                case "BALANCE":
                    String[] cardParameters = input[1].split(Common.SPACE, Common.TWO);
                    //metroNumber - cardParameters[0]
                    //balance - Integer.parseInt(cardParameters[1])
                    metroService.balance(cardParameters[0], Integer.parseInt(cardParameters[1]));
                    break;

                case "CHECK_IN":
                    String[] checkInParameters = input[1].split(Common.SPACE, Common.THREE);
                    //metroNumber - checkInParameters[0]
                    //passenger - checkInParameters[1] //type of passenger
                    //fromstation - checkInParameters[2]
                    metroService.checkIn(checkInParameters[0], checkInParameters[1], checkInParameters[2]);
                    break;
                
                case "PRINT_SUMMARY":
                    //print summary
                    metroService.print_summary();
                    break;
                    
                default:
                    break;
               }
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            throw new IOException("Error while reading input");
        }
    }
    
}
