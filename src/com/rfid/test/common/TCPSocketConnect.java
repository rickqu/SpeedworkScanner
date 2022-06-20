package com.rfid.test.common;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.rfid.test.callback.CallBackSockets;

public class TCPSocketConnect {
	
	private ServerSocket serverSocket = null;
	private static boolean isStartServer = false;
	private Thread threadSockets;
	

	public boolean startServer(int port,CallBackSockets callback) {
		try {
			serverSocket = new ServerSocket(port);
			isStartServer = true;
			if (isStartServer) {
				threadSockets = new Thread(new TCPServerSocket(callback));
				threadSockets.start();
			}
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
			isStartServer = false;
		}
		return false;
	}
	
	public boolean startServer(int port,int backlog,String bindAddrIP,CallBackSockets callback) {
		try {
			serverSocket = new ServerSocket(port,backlog, InetAddress.getByName(bindAddrIP));
			isStartServer = true;
			if (isStartServer) {
				threadSockets = new Thread(new TCPServerSocket(callback));
				threadSockets.start();
			}
			return true;
		} catch (IOException e1) {
			e1.printStackTrace();
			isStartServer = false;
			return false;
		}
	}
	
	public boolean stopServer() {
		try {
			if (isStartServer) {
				isStartServer = false;
				Thread.sleep(5);
				serverSocket.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
	
	public class TCPServerSocket implements Runnable {
		
		private CallBackSockets callback;
		
		public TCPServerSocket(CallBackSockets callback){
			this.callback = callback;
		}
		
		int ports = 0;
		public void run() {
			while (isStartServer) {
				try {
					Socket client = serverSocket.accept();
					if(null != client){
						System.out.println("client : " + client);
						callback.result(client, client.getInetAddress().getHostAddress(), client.getPort());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
