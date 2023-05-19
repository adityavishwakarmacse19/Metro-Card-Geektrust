package com.geektrust.backend;

import java.io.IOException;

import com.geektrust.backend.Services.StartService;
import com.geektrust.backend.Services.StartServiceImpl;

public class App {

	public static void main(String[] args) throws IOException  {
        StartService startService = new StartServiceImpl();
        startService.start(args);
	}

}

