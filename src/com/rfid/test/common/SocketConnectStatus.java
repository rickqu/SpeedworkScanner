package com.rfid.test.common;

import java.net.Socket;

import com.rfid.util.SystemType;

/**
 * Socket�������
 * @author zhu 2021-07-19  ע��Ŀǰ��windows����������ƽ̨ʱ���ܳ��ֲ����ݵ����
 *
 */
public class SocketConnectStatus implements Runnable {

	private Socket socket;
	private String device;
	private static boolean flag = true;

	public SocketConnectStatus(Socket socket, String device) {
		this.socket = socket;
		this.device = device;
	}
	
	public static void getStyleConfig(){
		int style = SystemType.config();
		if(style == 0){
			//windowsStyle();
		}else if(style == 1){
			//linuxStyle();
		}else if(style == 2){
			
		}
	}
	
	@Override
	public void run() {
		flag = true;
		try {
			while (flag) {
				socket.setOOBInline(true);
				socket.setKeepAlive(true);
				socket.setSoTimeout(10);
				socket.sendUrgentData(0xFF);
				Thread.sleep(1 * 1000);
			}
		} catch (Exception e) {
			System.out.println("�豸�Ͽ��� " + this.device + "\n");
			flag = false;
			//e.printStackTrace();
		}
	}
}
