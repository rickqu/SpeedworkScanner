package com.rfid.test.Discrete;

import java.time.Clock;

import com.rfid.callBack.CallBack.Discrete;
import com.rfid.test.Discrete.model.RFIDScanResult;
import com.rfid.test.Discrete.proxy.ControlServiceProxy;

class HTTPCallbackPoster implements Discrete {
	private final ControlServiceProxy controlServiceProxy;

	public HTTPCallbackPoster(final ControlServiceProxy controlServiceProxy) {
		this.controlServiceProxy = controlServiceProxy;
	}

	public void result(String data, String antennaNo, String deviceNo,String communicationMode, String temperature) {
		final RFIDScanResult rfidScanResult = new RFIDScanResult(data, Clock.systemUTC().millis(), antennaNo, deviceNo, communicationMode, temperature);
		try {
			controlServiceProxy.postResult(rfidScanResult);
			System.out.println("Logged " + rfidScanResult.toString());
		} catch (final Exception e) {
			System.err.println("Failed to publish scan: " + rfidScanResult + ". Reason: " + e);
		}
	}

	public void serialPortException(String name, boolean result) {
		System.out.println( "Error: " + name + "Result: " + result);
	}
}