package com.rfid.test.Discrete.stopController;

import java.util.Scanner;

public class ConsoleStopController implements StopController {

    private final String stopKeyword;
    private final Scanner scanner;

    public ConsoleStopController(final String stopKeyword, final Scanner scanner) {
        this.stopKeyword = stopKeyword;
        this.scanner = scanner;
    }

    @Override
    public boolean shouldStop() {
        return scanner.next().equals(stopKeyword);
    } 

    @Override
    public void close() {
        scanner.close();
    }
}
