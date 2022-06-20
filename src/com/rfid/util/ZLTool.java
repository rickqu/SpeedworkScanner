package com.rfid.util;

import com.rfid.service.ZLDMService;
import com.rfid.service.impl.ZLDMServiceImpl;

public class ZLTool {

	public final static int MAX_DEVICE_NUM = 50;
	
	public static ZLDMService handler = new ZLDMServiceImpl();
	
	public static byte selectedDevNo;
}
