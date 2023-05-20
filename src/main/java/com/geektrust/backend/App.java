package com.geektrust.backend;

import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException  {
        StartService startService = new StartServiceImpl();
        startService.start(args);
	}

}

