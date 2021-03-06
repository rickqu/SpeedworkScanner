package com.rfid.test.Discrete;

import java.util.concurrent.atomic.AtomicBoolean;

import com.rfid.test.Discrete.model.SerialSettings;
import com.rfid.test.Discrete.proxy.ControlServiceProxy;
import com.rfid.uhf.controller.impl.ReaderDiscrete;
import com.rfid.uhf.service.ReaderDisService;

public class Reader implements Runnable {

    private final AtomicBoolean keepRunning;
	private final HTTPCallbackPoster callBackDiscrete;
	private final ControlServiceProxy controlServiceProxy;
	private final ReaderDisService service;

    public Reader(
		final AtomicBoolean keepRunning, 
		final HTTPCallbackPoster callBackDiscrete,
		final ControlServiceProxy controlServiceProxy,
		final ReaderDisService service
	) {
        this.keepRunning = keepRunning;
		this.callBackDiscrete = callBackDiscrete;
		this.controlServiceProxy = controlServiceProxy;
		this.service = service;
    }

    @Override
    public void run() {
		final SerialSettings serialSettings;
		try {
			serialSettings = controlServiceProxy.getSerialSettings();
		} catch (Exception e) {
			System.err.println("There was an error trying to access control service: " + e);
			return;
		}
		ReaderDiscrete reader = service.connect(serialSettings.port(), serialSettings.BaudRate(), callBackDiscrete);
		if(null == reader){
			return;
		}
		service.beginInv(reader);
		while (keepRunning.get()) {
            try {
			    Thread.sleep(1000);
            } catch (Exception e) {
                ;
            }
		}
		service.stopInv(reader);
		service.disconnect(reader);
    }
}
