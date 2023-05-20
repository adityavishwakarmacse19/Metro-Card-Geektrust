package com.geektrust.backend;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.geektrust.backend.StartService;

public class StartServiceTest {
    StartService startService = new StartService();

    @Test
    public void callStartMethodThrowsException () throws IOException {
        Assertions.assertThrows(IOException.class, () -> {
            startService.start(new String[]{"url1"});
        });
    }

    @Test
    public void callStartMethod () throws IOException {
        startService.start(new String[]{"src/test/java/resources/input.txt"});
    }
}
