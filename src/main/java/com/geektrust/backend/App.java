package com.geektrust.backend;

import java.io.IOException;

import com.geektrust.backend.services.StartService;
import com.geektrust.backend.services.StartServiceImpl;

public class App {

	public static void main(String[] args) throws IOException  {
        StartService startService = new StartServiceImpl();
        startService.start(args);
	}

}

