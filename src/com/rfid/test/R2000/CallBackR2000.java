package com.rfid.test.R2000;

import com.rfid.bean.GPS_DataInfo;
import com.rfid.callBack.CallBack.R2000;

class CallBackR2000 implements R2000{

	public void readData(String data, String rssi, String antennaNo, String deviceNo,String direction,String communicationMode){
		System.out.println("EPC " + data + " RSSI " + rssi + " antennaNo " + antennaNo + " deviceNo " + deviceNo);
	}

	public void serialPortException(String name, boolean result) {
		System.out.println(" ���ں�: " + name + " ���: " + result);
	}

	@Override
	public void heartBeat(String value,String deviceNo,String communicationMode) {
		StringBuffer sb = new StringBuffer();
		sb.append(" ����ֵ: ");
		sb.append(value);
		sb.append(" �豸��: ");
		sb.append(deviceNo);
		sb.append(" IP�򴮿ں�: ");
		sb.append(communicationMode);
		System.out.println(sb.toString());
	}

	@Override
	public void infraredTrigger(boolean direction,String value,String deviceNo,String communicationMode) {
		StringBuffer sb = new StringBuffer();
		sb.append(" ���ⴥ�������뷽��(0��1): ");
		sb.append(direction);
		sb.append(" ���ⴥ��ֵ: ");
		sb.append(value);
		sb.append(" �豸��: ");
		sb.append(deviceNo);
		sb.append(" IP�򴮿ں�: ");
		sb.append(communicationMode);
		System.out.println(sb.toString());
	}

	@Override
	public void onceReadTagEnd(boolean result) {
		System.out.println(" ���ζ�������: " + result);
	}

	@Override
	public void GPSInfo(GPS_DataInfo info) {
		System.out.println(info.getDeviceNo());
		System.out.println(info.getOriginalData());
	}
}