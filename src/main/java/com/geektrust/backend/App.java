package com.geektrust.backend;

import java.io.IOException;

import com.geektrust.backend.Service.StartService;
import com.geektrust.backend.Service.StartServiceImpl;

public class App {

	public static void main(String[] args) throws IOException  {
        StartService startService = new StartServiceImpl();
        startService.start(args);
	}

}

