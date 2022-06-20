package com.rfid.test.Dev2_4gDemo;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import com.rfid.bean.Reader2_4g;
import com.rfid.test.callback.CallBackSockets;
import com.rfid.test.common.SocketConnectStatus;
import com.rfid.test.common.TCPSocketConnect;
import com.rfid.uhf.service.Reader2_4gService;
import com.rfid.uhf.service.impl.Reader2_4gServiceImpl;

public class TCPSocketConnect2_4g implements CallBackSockets{
	
	private Thread threadHeartbeat;
	public static List<Reader2_4g> list = new ArrayList< Reader2_4g>();
	public Reader2_4gService service = new Reader2_4gServiceImpl();

	public static int port = 8032;
	
	public static void main(String []args) throws InterruptedException{
		TCPSocketConnect connect = new TCPSocketConnect();
		connect.startServer(port,new TCPSocketConnect2_4g());
		while(true);//阻塞，让程序一直运行
		//connect.stopServer();
	}
	
	@Override
	public void result(Socket socket, String IP, int port) {
		Reader2_4g reader = service.tcpServerConnect(socket, new CallBackDev2_4g());
		service.beginInv(reader);
		//service.stopInv(reader);
		list.add(reader);//对象添加到集合中
		threadHeartbeat = new Thread(new SocketConnectStatus(socket,IP));//心跳
		threadHeartbeat.start();
	}
}
