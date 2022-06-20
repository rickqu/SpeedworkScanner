package com.rfid.test.R2000;

import com.rfid.uhf.controller.impl.ReaderR2000;
import com.rfid.uhf.service.ReaderR2000Service;
import com.rfid.uhf.service.impl.ReaderR2000ServiceImpl;

public class R2000Demo {
	
	public static String IPorSerialPort = "COM1"; //IP或串口号  (注意：IP地址对应端口，串口对应波特率)
	public static int portOrBaudRate = 115200;    //端口(默认为20058)或波特率(默认为115200)
	
	public static void main(String[] args) throws Exception {
		ReaderR2000Service service = new ReaderR2000ServiceImpl();
		ReaderR2000 reader = service.connect(IPorSerialPort, portOrBaudRate, new CallBackR2000());//连接设备
		if(null == reader){
			return;
		}
		service.beginInvV2(reader);	//连续读卡
		Thread.sleep(1000 * 10);
		service.stopInvV2(reader);	//停止连续读卡
		service.disconnect(reader);	////断开连接,释放线程
	}
}


