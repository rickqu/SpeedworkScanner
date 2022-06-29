package com.rfid.test.Discrete;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.security.auth.callback.Callback;

import com.rfid.uhf.controller.impl.ReaderDiscrete;
import com.rfid.uhf.service.ReaderDisService;
import com.rfid.uhf.service.impl.ReaderDisServiceImpl;

public class Reader implements Runnable {

	private static final String IPorSerialPort = "COM3"; 
	private static final int portOrBaudRate = 9600;    

    private final AtomicBoolean keepRunning;
	private final HTTPCallbackPoster callBackDiscrete;

    public Reader(final AtomicBoolean keepRunning, final HTTPCallbackPoster callBackDiscrete) {
        this.keepRunning = keepRunning;
		this.callBackDiscrete = callBackDiscrete;
    }

    @Override
    public void run() {
		ReaderDisService service = new ReaderDisServiceImpl();
		ReaderDiscrete reader = service.connect(IPorSerialPort, portOrBaudRate, callBackDiscrete);
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
