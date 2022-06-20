package com.rfid.test.callback;

import java.net.Socket;

public interface CallBackSockets {
	void result(Socket socket, String IP, int port);
}
