package com.rfid.test.Discrete;

import java.net.http.HttpClient;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rfid.test.Discrete.proxy.ControlServiceProxy;
import com.rfid.test.Discrete.stopController.ConsoleStopController;
import com.rfid.test.Discrete.stopController.StopController;
import com.rfid.uhf.service.ReaderDisService;
import com.rfid.uhf.service.impl.ReaderDisServiceImpl;

public class DiscreteDemo {

	private static final String STOP = "STOP";
	
	public static void main(String[] args) throws Exception {
		final AtomicBoolean keepRunning = new AtomicBoolean(true);
		final HttpClient httpClient = HttpClient.newHttpClient();
		final ObjectMapper objectMapper = new ObjectMapper();
		final ControlServiceProxy controlServiceProxy = new ControlServiceProxy(args[0], httpClient, objectMapper);
		final HTTPCallbackPoster httpCallbackPoster = new HTTPCallbackPoster(controlServiceProxy);
		final ReaderDisService readerDisService = new ReaderDisServiceImpl();
		final Reader reader = new Reader(keepRunning, httpCallbackPoster, controlServiceProxy, readerDisService);
		final Thread readerThread = new Thread(reader);
		final Scanner scanner = new Scanner(System.in);
		final StopController stopController = new ConsoleStopController(STOP, scanner);

		readerThread.start();

		while (true) {
			if (stopController.shouldStop()) {
				keepRunning.set(false);
				stopController.close();
				break;
			}
		}
		readerThread.join();
	}
}


