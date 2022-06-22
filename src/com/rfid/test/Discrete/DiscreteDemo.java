package com.rfid.test.Discrete;

import com.rfid.uhf.controller.impl.ReaderDiscrete;
import com.rfid.uhf.service.ReaderDisService;
import com.rfid.uhf.service.impl.ReaderDisServiceImpl;

public class DiscreteDemo {
	
	public static String IPorSerialPort = "COM3"; //IP�򴮿ں�  (ע�⣺IP��ַ��Ӧ�˿ڣ����ڶ�Ӧ������)
	public static int portOrBaudRate = 9600;    //�˿�(Ĭ��Ϊ8000)������(Ĭ��Ϊ115200)
	
	public static void main(String[] args) throws Exception {
		ReaderDisService service = new ReaderDisServiceImpl();
		ReaderDiscrete reader = service.connect(IPorSerialPort, portOrBaudRate, new CallBackDiscrete());//�����豸
		if(null == reader){
			return;
		}
		service.beginInv(reader);	//��������
		while (true) {
			Thread.sleep(300);
		}
		/*
		service.stopInv(reader); 	//ֹͣ��������
		service.disconnect(reader); //�Ͽ�����,�ͷ��߳�
	*/
	}
}


