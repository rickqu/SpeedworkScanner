package com.rfid.test.Discrete;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import com.rfid.test.callback.CallBackSockets;
import com.rfid.test.common.SocketConnectStatus;
import com.rfid.test.common.TCPSocketConnect;
import com.rfid.uhf.controller.impl.ReaderDiscrete;
import com.rfid.uhf.service.ReaderDisService;
import com.rfid.uhf.service.impl.ReaderDisServiceImpl;

public class TCPSocketConnectDiscrete implements CallBackSockets{
	
	private Thread threadHeartbeat;
	private static String device = null;
	public static List<ReaderDiscrete> list = new ArrayList<ReaderDiscrete>();
	public ReaderDisService service = new ReaderDisServiceImpl();
	public static int port = 4196;
	
	public static void main(String []args) throws InterruptedException{
		TCPSocketConnect connect = new TCPSocketConnect();
		connect.startServer(port,new TCPSocketConnectDiscrete());
		while(true);//阻塞，让程序一直运行
		//connect.stopServer();
	}
	
	@Override
	public void result(Socket socket, String IP, int port) {
		ReaderDiscrete reader = service.tcpServerConnect(socket, new CallBackDiscrete());//连接设备
		service.beginInv(reader);//连续读卡
		//service.stopInv(reader);//停止连续读卡
		list.add(reader);
		threadHeartbeat = new Thread(new SocketConnectStatus(socket,device));
		threadHeartbeat.start();
	}
}
