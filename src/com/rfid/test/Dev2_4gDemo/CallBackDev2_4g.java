package com.rfid.test.Dev2_4gDemo;

import com.rfid.bean.EnumGPSType;
import com.rfid.bean.GPGGA;
import com.rfid.bean.GPS_DataInfo;
import com.rfid.bean.OutputDataHighFrquency;
import com.rfid.bean.OutputDataInfo;
import com.rfid.callBack.CallBack.Dev2_4g;

class CallBackDev2_4g implements Dev2_4g {
	
	@Deprecated
	public void result(String data, String antennaNo, String deviceNo, String rssi,	String alarm, String privateData, String communicationMode) {
//		StringBuffer sb = new StringBuffer();
//		sb.append("	ID：" + data);
//		sb.append(" 天线号 ：" + antennaNo);
//		sb.append(" 设备号 ：" + deviceNo);
//		sb.append(" IP或串口号 ：" + communicationMode);
//		System.out.println(sb.toString());
	}

	@Deprecated
	public void result(String data, String antennaNo,String deviceNo,String rssi,String alarm,String privateData, String communicationMode,String temperature) {
//		StringBuffer sb = new StringBuffer();
//		sb.append("	ID：" + data);
//		sb.append(" 天线号 ：" + antennaNo);
//		sb.append(" 设备号 ：" + deviceNo);
//		sb.append(" IP或串口号 ：" + communicationMode);
//		System.out.println(sb.toString());
	}

	@Override
	public void serialPortException(String name, boolean result) {
		StringBuffer sb = new StringBuffer();
		sb.append(" 串口号: " + name);
		System.out.println(sb.toString());
	}

	@Override
	public void result(OutputDataInfo info) {
		StringBuffer sb = new StringBuffer();
		sb.append(" ID: " + info.getId());
		sb.append(" 天线号: " + info.getAntennaNo());
		sb.append(" 设备号: " + info.getDeviceNo());
		sb.append(" RSSI(信号强度): " + info.getRssi());
		sb.append(" IP或串口号 ：" + info.getCommunicationMode());
		
		//不同的标签，私有数据定义也不同
		sb.append(" 私有数据: " + info.getPrivateData()); 
		
		//温度值有些标签可能不带湿度值传感器，相应的值是无效的
		sb.append(" 温度值 ： " + info.getTemperature());	
		
		//时间截当前回调数据的时间
//		sb.append(" 时间截 ： " + info.getTimeStamp());		
		
		//标签类型参考Java带UI的Demo
		sb.append(" 标签类型 ： " + info.getTagType());		
		
		//用户自定义数据,比如区别设备与设备的型号
//		sb.append(" 用户数据 ： " + info.getUserData());	
		
		//不同的标签，报警值定义也不同
		sb.append(" 报警值: " + info.getAlarm());			
		System.out.println(sb.toString());
	}
	
	@Override
	public void GPSInfo(GPS_DataInfo GPSInfo) { //需设备带有GPS的硬件才会有相关的数据
		//GPS原始数据
		String originalData = GPSInfo.getOriginalData();  
		
		 //设备号
		String deviceNo = GPSInfo.getDeviceNo();    
		
		//GPS数据类型
		EnumGPSType type = GPSInfo.getType();			
		
		//GPGGA类型
		GPGGA GPGGAType = GPSInfo.getGPGGAType();			
		
		//1.UTC 时间，hhmmss（时分秒）格式
		String time = GPSInfo.getGPGGAType().getTime();		
		
		//2.定位状态 A：有效定位 V：无效定位
		boolean positioningState = GPSInfo.getGPGGAType().isPositioningState();	  
		
		//3.纬度 ddmm.mmmm（度分）格式（前面的 0 也将被传输）
		String latitude = GPSInfo.getGPGGAType().getLatitude();						
		
		//4.纬度方向 N：北纬 S：南纬
		String latitudeDirection = GPSInfo.getGPGGAType().getLatitudeDirection();   
		
		//5.经度 dddmm.mmmm（度分）格式（前面的 0 也将被传输）
		String longitude = GPSInfo.getGPGGAType().getLongitude();					
		
		//6.经度方向 E：东经 W：西经
		String longitudeDirection = GPSInfo.getGPGGAType().getLongitudeDirection(); 
		
		//7.地面速率（000.0~999.9 节，前面的 0 也将被传输）
		String groundSpeed = GPSInfo.getGPGGAType().getGroundSpeed();				
		
		//8.地面航向（000.0~359.9 度，以真北为参考基准，前面的 0 也将被传输）
		String groundSailingDirections = GPSInfo.getGPGGAType().getGroundSailingDirections();
		
		//9.UTC 日期，ddmmyy（日月年）格式
		String date = GPSInfo.getGPGGAType().getDate();			
		
		//10.磁偏角（000.0~180.0 度，前面的 0 也将被传输）
		String magneticDeclination = GPSInfo.getGPGGAType().getMagneticDeclination(); 
		
		//11.磁偏角方向 E：东 W：西
		String magneticDeclinationDirections = GPSInfo.getGPGGAType().getMagneticDeclinationDirections();
		
		//模式指示 A：自主定位 D：差分 E：估算 N：数据无效
		String patternInstructions = GPSInfo.getGPGGAType().getPatternInstructions(); 
		
		StringBuffer sb = new StringBuffer();
		sb.append(" GPS原始数据: ");
		sb.append(originalData);
		sb.append(" 设备号: ");
		sb.append(deviceNo);
		sb.append(" GPS数据类型: ");
		sb.append(type);
		sb.append(" GPGGA类型: ");
		sb.append(GPGGAType);
		sb.append(" UTC 时间，hhmmss（时分秒）格式: ");
		sb.append(time);
		sb.append(" 定位状态 A：有效定位 V：无效定位: ");
		sb.append(positioningState);
		sb.append(" 纬度 ddmm.mmmm（度分）格式（前面的 0 也将被传输）: ");
		sb.append(latitude);
		sb.append(" 纬度方向 N：北纬 S：南纬: ");
		sb.append(latitudeDirection);
		sb.append(" 经度 dddmm.mmmm（度分）格式（前面的 0 也将被传输）: ");
		sb.append(longitude);
		sb.append(" 经度方向 E：东经 W：西经: ");
		sb.append(longitudeDirection);
		sb.append(" 地面速率（000.0~999.9 节，前面的 0 也将被传输）: ");
		sb.append(groundSpeed);
		sb.append(" 地面航向（000.0~359.9 度，以真北为参考基准，前面的 0 也将被传输）: ");
		sb.append(groundSailingDirections);
		sb.append(" UTC 日期，ddmmyy（日月年）格式: ");
		sb.append(date);
		sb.append(" 磁偏角（000.0~180.0 度，前面的 0 也将被传输）: ");
		sb.append(magneticDeclination);
		sb.append(" 磁偏角方向 E：东 W：西: ");
		sb.append(magneticDeclinationDirections);
		sb.append(" 模式指示 A：自主定位 D：差分 E：估算 N：数据无效: ");
		sb.append(patternInstructions);
		System.out.println(sb.toString());
	}

//	@Override
//	public void heartBeat(String deviceNo,String deviceVersion,String readCardStatus,String stateTrigger,String readHeadState,long timeStamp,String communicationMode) {
//		StringBuffer sb = new StringBuffer();
//		sb.append(" 设备号: " + deviceNo);
//		
//		sb.append(" 设备版本号: " + deviceVersion);
//		
//		//设备处在读卡状态（有是时01）,00则表示已停止读
//		sb.append(" 读卡状态: " + readCardStatus);
//		
//		//触发状态，02或01表示检测到触发，00无触发
//		sb.append(" 触发状态: " + stateTrigger);
//		
//		//读头错误状态，00正常，01出现过超时，02出现过停止失败
//		sb.append(" 读头错误状态: " + readHeadState);
//		
//		//时间截当前回调数据的时间
//		sb.append(" 时间截 ： " + timeStamp);	
//		
//		sb.append(" IP或串口号 ：" + communicationMode);
//		System.out.println(sb.toString());
//	}

	@Override
	public void onlyHighFrquency(OutputDataHighFrquency outputData) {
		//IP或串口号
		String communicationMode = outputData.getCommunicationMode();
		//块号
		int block = outputData.getBlock();
		//设备号
		String deviceNo = outputData.getDeviceNo();
		//块数据
		String blockData = outputData.getBlockData();
		StringBuffer sb = new StringBuffer();
		sb.append(" IP或串口号: ");
		sb.append(communicationMode);
		sb.append(" 块号: ");
		sb.append(block);
		sb.append(" 设备号: ");
		sb.append(deviceNo);
		sb.append(" 块数据: ");
		sb.append(blockData);
		System.out.println(sb.toString());
	}

	@Override
	public void heartBeat(String deviceNo, String deviceVersion,String readCardStatus, String stateTrigger, String readHeadState,long timeStamp, String communicationMode) {
		// TODO Auto-generated method stub
	}
}