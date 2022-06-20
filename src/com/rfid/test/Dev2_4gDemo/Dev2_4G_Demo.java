package com.rfid.test.Dev2_4gDemo;

import com.rfid.bean.Reader2_4g;
import com.rfid.uhf.service.Reader2_4gService;
import com.rfid.uhf.service.impl.Reader2_4gServiceImpl;

public class Dev2_4G_Demo {
	
	public static String IPorSerialPort = "COM1"; //IP或串口号  (注意：IP地址对应端口，串口对应波特率)
	public static int portOrBaudRate = 115200;    //端口(默认为8000)或波特率(默认为115200)
	
	public static void main(String[] args) throws InterruptedException {
		Reader2_4gService service = new Reader2_4gServiceImpl();
		Reader2_4g reader = service.connect(IPorSerialPort,portOrBaudRate, new CallBackDev2_4g());//建立连接
		if(null == reader){
			return;
		}
		service.beginInv(reader); 	//连续读卡
		Thread.sleep(1000 * 100);	
		service.stopInv(reader);  	//停止连续读卡
		service.disconnect(reader); //断开连接,释放当前线程
	}
}
