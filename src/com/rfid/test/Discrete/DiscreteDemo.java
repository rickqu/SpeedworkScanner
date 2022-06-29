package com.rfid.test.Discrete;

import java.net.http.HttpClient;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rfid.test.Discrete.proxy.ControlServiceProxy;
import com.rfid.test.Discrete.stopController.ConsoleStopController;
import com.rfid.test.Discrete.stopController.StopController;

public class DiscreteDemo {

	private static final String STOP = "STOP";
	private static final String URL_BASE = "http://localhost:8030/";
	private static final String SETTING_ENDPOINT = "localhost";
	private static final String URL_SETTING = URL_BASE + SETTING_ENDPOINT;
	
	public static void main(String[] args) throws Exception {
		AtomicBoolean keepRunning = new AtomicBoolean(true);
		final HTTPCallbackPoster httpCallbackPoster = new HTTPCallbackPoster();
		final HttpClient httpClient = HttpClient.newHttpClient();
		final ObjectMapper objectMapper = new ObjectMapper();
		final ControlServiceProxy controlServiceProxy = new ControlServiceProxy(URL_BASE, httpClient, objectMapper);
		final Reader reader = new Reader(keepRunning, httpCallbackPoster, controlServiceProxy);
		final Thread readerThread = new Thread(reader);
		final Scanner scanner = new Scanner(System.in);
		final StopController stopController = new ConsoleStopController(STOP, scanner);
		readerThread.start();

		while (true) {
			if (stopController.shouldStop()) {
				keepRunning.set(false);
				scanner.close();
				break;
			}
		}
		readerThread.join();
	}
}


