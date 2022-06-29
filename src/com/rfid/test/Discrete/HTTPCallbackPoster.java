package com.rfid.test.Discrete;

import com.rfid.callBack.CallBack.Discrete;

class HTTPCallbackPoster implements Discrete {

	public void result(String data, String antennaNo, String deviceNo,String communicationMode, String temperature) {
		System.out.println("EPC " + data + " ���ߺ�: " + antennaNo + " �豸��: " + deviceNo + " IP�򴮿ں�: " + communicationMode);
	}

	public void serialPortException(String name, boolean result) {
		System.out.println( " ���ںţ� " + name + " ���: " + result);
	}
}