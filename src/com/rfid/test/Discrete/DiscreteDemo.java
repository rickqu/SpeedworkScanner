package com.rfid.test.Discrete;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class DiscreteDemo {

	private static final String STOP = "STOP";
	
	public static void main(String[] args) throws Exception {
		AtomicBoolean keepRunning = new AtomicBoolean(true);
		final Thread readerThread = new Thread(new Reader(keepRunning));
		final Scanner scanner = new Scanner(System.in);
		readerThread.start();
		while (true) {
			if (scanner.next().equals(STOP)) {
				keepRunning.set(false);
				scanner.close();
				break;
			}
		}
		readerThread.join();
	}
}


