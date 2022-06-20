package com.rfid.test.Discrete;

import com.rfid.callBack.CallBack.Discrete;

class CallBackDiscrete implements Discrete {

	public void result(String data, String antennaNo, String deviceNo,String communicationMode, String temperature) {
		System.out.println("EPC " + data + " 天线号: " + antennaNo + " 设备号: " + deviceNo + " IP或串口号: " + communicationMode);
	}

	public void serialPortException(String name, boolean result) {
		System.out.println( " 串口号： " + name + " 结果: " + result);
	}
}