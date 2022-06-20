package com.rfid.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.rfid.bean.Reader2_4g;
import com.rfid.uhf.controller.impl.ReaderDiscrete;
import com.rfid.uhf.controller.impl.ReaderR2000;

public class ReaderUtil {
	
	public static Map<String, ReaderR2000> r2ks = new HashMap<String, ReaderR2000>();
	public static Map<String, ReaderDiscrete> discretes = new HashMap<String, ReaderDiscrete>();
	public static Map<String, Reader2_4g> dev2Point4g = new HashMap<String, Reader2_4g>();
	
	public static ResourceBundle language = null;
	
	/**
	 * 1 R2000 Device
	 * 2 Discrete Device 
	 * 3 2.4G Device
	 */
	//public static int deviceType = 1;
	
	public static int count = 0;
	
	static{
		String key = "Language";
		String value = Sunini.getIniKey(key); 
		if(value.equals("0")){
			language = ResourceBundle.getBundle("language",Locale.ENGLISH);
		}else if(value.equals("1")){
			language = ResourceBundle.getBundle("language",Locale.CHINA);
		}else{
			language = ResourceBundle.getBundle("language",Locale.ENGLISH);
		}
	}
}
