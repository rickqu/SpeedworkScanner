package com.rfid.test.R2000;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import com.rfid.test.callback.CallBackSockets;
import com.rfid.test.common.SocketConnectStatus;
import com.rfid.test.common.TCPSocketConnect;
import com.rfid.uhf.controller.impl.ReaderR2000;
import com.rfid.uhf.service.ReaderR2000Service;
import com.rfid.uhf.service.impl.ReaderR2000ServiceImpl;

public class TCPSocketConnectR2000 implements CallBackSockets{
	
	private Thread sockets;
	private static String device = null;
	public static List<ReaderR2000> list = new ArrayList<ReaderR2000>();
	public static int port = 20058;
	
	public static void main(String []args) throws InterruptedException{
		TCPSocketConnect connect = new TCPSocketConnect();
		connect.startServer(port,new TCPSocketConnectR2000());
		while(true);//阻塞，让程序一直运行
		//connect.stopServer();
	}

	@Override
	public void result(Socket socket, String IP, int port) {
		System.out.println(socket.getInetAddress().getHostName());
		device = socket.getInetAddress().getHostName();
		ReaderR2000Service service = new ReaderR2000ServiceImpl();
		ReaderR2000 reader = service.tcpServerConnect(socket, new CallBackR2000());//连接设备
		//service.beginInvV2(reader);//连续读卡
		//service.stopInvV2(reader);//停止连续读卡
		list.add(reader);
		sockets = new Thread(new SocketConnectStatus(socket,device));
		sockets.start();
	}
}
