package com.geektrust.backend;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import com.geektrust.backend.constants.Common;
import com.geektrust.backend.constants.Passenger;
import com.geektrust.backend.constants.StationName;
import com.geektrust.backend.models.Journey;
import com.geektrust.backend.services.CardService;
import com.geektrust.backend.services.CheckInService;
import com.geektrust.backend.services.PrintSummaryService;
import com.geektrust.backend.services.Impl.CheckInServiceImpl;
import com.geektrust.backend.services.Impl.MetroCardServiceImpl;
import com.geektrust.backend.services.Impl.PrintSummaryServiceImpl;

public class StartService{

    public void start(String[] args) throws IOException {
        CardService metroService = new MetroCardServiceImpl();
        CheckInService checkInService = new CheckInServiceImpl(metroService);
        PrintSummaryService printSummaryService = new PrintSummaryServiceImpl(checkInService);

        // code to read from file passed as command line argument
        try {
            
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);

            while (sc.hasNextLine()) {

               // code to process input commands
               String[] input = sc.nextLine().split(Common.SPACE, Common.TWO);
               switch (input[0]) {

                case "BALANCE":
                    String[] cardParameters = input[1].split(Common.SPACE, Common.TWO);
                    //cardNumber - cardParameters[0]
                    //balance - Integer.parseInt(cardParameters[1])
                    metroService.balance(cardParameters[0], Integer.parseInt(cardParameters[1]));
                    break;

                case "CHECK_IN":
                    String[] journeyParameters = input[1].split(Common.SPACE, Common.THREE);
                    //cardNumber - journeyParameters[0]
                    //passenger - Passenger.valueOf(journeyParameters[1])
                    //fromstation - StationName.valueOf(journeyParameters[2])
                    checkInService.checkInPassenger(new Journey(journeyParameters[0], Passenger.valueOf(journeyParameters[1]), StationName.valueOf(journeyParameters[2])));
                    break;
                
                case "PRINT_SUMMARY":
                    //print summary
                    printSummaryService.printSummary();
                    break;
                    
                default:
                    break;
               }
            }
            sc.close();
        } catch (IOException e) {
            throw new IOException("Error while reading input");
        }
    }
    
}
