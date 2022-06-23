package com.rfid.test.Discrete;

import java.util.concurrent.atomic.AtomicBoolean;


import com.rfid.uhf.controller.impl.ReaderDiscrete;
import com.rfid.uhf.service.ReaderDisService;
import com.rfid.uhf.service.impl.ReaderDisServiceImpl;

public class Reader implements Runnable {

	private static final String IPorSerialPort = "COM3"; 
	private static final int portOrBaudRate = 9600;    
    private static final int SLEEP_MS = 300;

    private final AtomicBoolean keepRunning;

    public Reader(final AtomicBoolean keepRunning) {
        this.keepRunning = keepRunning;
    }

    @Override
    public void run() {
		ReaderDisService service = new ReaderDisServiceImpl();
        ReaderDiscrete reader;
        CallBackDiscrete callBackDiscrete = null;
        try {
            callBackDiscrete = new CallBackDiscrete();
		    reader = service.connect(IPorSerialPort, portOrBaudRate, callBackDiscrete);//�����豸
        } catch (Exception e) {
            System.out.println(e);
            if (callBackDiscrete != null) {
                callBackDiscrete.close();
            }
            return;
        }
		if(null == reader){
			return;
		}
		service.beginInv(reader);	//��������
		while (keepRunning.get()) {
            try {
			    Thread.sleep(SLEEP_MS);
            } catch (Exception e) {
                ;
            }
		}
        callBackDiscrete.close();
		service.stopInv(reader); 	//ֹͣ��������
		service.disconnect(reader); //�Ͽ�����,�ͷ��߳�
    }
}
