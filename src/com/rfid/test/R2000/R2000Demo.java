package com.rfid.test.R2000;

import com.rfid.uhf.controller.impl.ReaderR2000;
import com.rfid.uhf.service.ReaderR2000Service;
import com.rfid.uhf.service.impl.ReaderR2000ServiceImpl;

public class R2000Demo {
	
	public static String IPorSerialPort = "COM1"; //IP�򴮿ں�  (ע�⣺IP��ַ��Ӧ�˿ڣ����ڶ�Ӧ������)
	public static int portOrBaudRate = 115200;    //�˿�(Ĭ��Ϊ20058)������(Ĭ��Ϊ115200)
	
	public static void main(String[] args) throws Exception {
		ReaderR2000Service service = new ReaderR2000ServiceImpl();
		ReaderR2000 reader = service.connect(IPorSerialPort, portOrBaudRate, new CallBackR2000());//�����豸
		if(null == reader){
			return;
		}
		service.beginInvV2(reader);	//��������
		Thread.sleep(1000 * 10);
		service.stopInvV2(reader);	//ֹͣ��������
		service.disconnect(reader);	////�Ͽ�����,�ͷ��߳�
	}
}


