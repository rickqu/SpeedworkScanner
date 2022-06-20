package com.rfid.test.R2000;

import com.rfid.bean.GPS_DataInfo;
import com.rfid.callBack.CallBack.R2000;

class CallBackR2000 implements R2000{

	public void readData(String data, String rssi, String antennaNo, String deviceNo,String direction,String communicationMode){
		System.out.println("EPC " + data + " RSSI " + rssi + " antennaNo " + antennaNo + " deviceNo " + deviceNo);
	}

	public void serialPortException(String name, boolean result) {
		System.out.println(" 串口号: " + name + " 结果: " + result);
	}

	@Override
	public void heartBeat(String value,String deviceNo,String communicationMode) {
		StringBuffer sb = new StringBuffer();
		sb.append(" 心跳值: ");
		sb.append(value);
		sb.append(" 设备号: ");
		sb.append(deviceNo);
		sb.append(" IP或串口号: ");
		sb.append(communicationMode);
		System.out.println(sb.toString());
	}

	@Override
	public void infraredTrigger(boolean direction,String value,String deviceNo,String communicationMode) {
		StringBuffer sb = new StringBuffer();
		sb.append(" 红外触发进或入方向(0或1): ");
		sb.append(direction);
		sb.append(" 红外触发值: ");
		sb.append(value);
		sb.append(" 设备号: ");
		sb.append(deviceNo);
		sb.append(" IP或串口号: ");
		sb.append(communicationMode);
		System.out.println(sb.toString());
	}

	@Override
	public void onceReadTagEnd(boolean result) {
		System.out.println(" 单次读卡结束: " + result);
	}

	@Override
	public void GPSInfo(GPS_DataInfo info) {
		System.out.println(info.getDeviceNo());
		System.out.println(info.getOriginalData());
	}
}