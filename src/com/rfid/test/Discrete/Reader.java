package com.rfid.test.Discrete;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.security.auth.callback.Callback;

import com.rfid.test.Discrete.model.SerialSettings;
import com.rfid.test.Discrete.proxy.ControlServiceProxy;
import com.rfid.uhf.controller.impl.ReaderDiscrete;
import com.rfid.uhf.service.ReaderDisService;
import com.rfid.uhf.service.impl.ReaderDisServiceImpl;

public class Reader implements Runnable {

    private final AtomicBoolean keepRunning;
	private final HTTPCallbackPoster callBackDiscrete;
	private final ControlServiceProxy controlServiceProxy;

    public Reader(final AtomicBoolean keepRunning, final HTTPCallbackPoster callBackDiscrete, final ControlServiceProxy controlServiceProxy) {
        this.keepRunning = keepRunning;
		this.callBackDiscrete = callBackDiscrete;
		this.controlServiceProxy = controlServiceProxy;
    }

    @Override
    public void run() {
		ReaderDisService service = new ReaderDisServiceImpl();
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
			    Thread.sleep(300);
            } catch (Exception e) {
                ;
            }
		}
		service.stopInv(reader);
		service.disconnect(reader);
    }
}
