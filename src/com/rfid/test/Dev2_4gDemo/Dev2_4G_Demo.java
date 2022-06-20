package com.rfid.test.Dev2_4gDemo;

import com.rfid.bean.Reader2_4g;
import com.rfid.uhf.service.Reader2_4gService;
import com.rfid.uhf.service.impl.Reader2_4gServiceImpl;

public class Dev2_4G_Demo {
	
	public static String IPorSerialPort = "COM1"; //IP�򴮿ں�  (ע�⣺IP��ַ��Ӧ�˿ڣ����ڶ�Ӧ������)
	public static int portOrBaudRate = 115200;    //�˿�(Ĭ��Ϊ8000)������(Ĭ��Ϊ115200)
	
	public static void main(String[] args) throws InterruptedException {
		Reader2_4gService service = new Reader2_4gServiceImpl();
		Reader2_4g reader = service.connect(IPorSerialPort,portOrBaudRate, new CallBackDev2_4g());//��������
		if(null == reader){
			return;
		}
		service.beginInv(reader); 	//��������
		Thread.sleep(1000 * 100);	
		service.stopInv(reader);  	//ֹͣ��������
		service.disconnect(reader); //�Ͽ�����,�ͷŵ�ǰ�߳�
	}
}
