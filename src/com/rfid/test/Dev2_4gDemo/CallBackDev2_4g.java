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
//		sb.append("	ID��" + data);
//		sb.append(" ���ߺ� ��" + antennaNo);
//		sb.append(" �豸�� ��" + deviceNo);
//		sb.append(" IP�򴮿ں� ��" + communicationMode);
//		System.out.println(sb.toString());
	}

	@Deprecated
	public void result(String data, String antennaNo,String deviceNo,String rssi,String alarm,String privateData, String communicationMode,String temperature) {
//		StringBuffer sb = new StringBuffer();
//		sb.append("	ID��" + data);
//		sb.append(" ���ߺ� ��" + antennaNo);
//		sb.append(" �豸�� ��" + deviceNo);
//		sb.append(" IP�򴮿ں� ��" + communicationMode);
//		System.out.println(sb.toString());
	}

	@Override
	public void serialPortException(String name, boolean result) {
		StringBuffer sb = new StringBuffer();
		sb.append(" ���ں�: " + name);
		System.out.println(sb.toString());
	}

	@Override
	public void result(OutputDataInfo info) {
		StringBuffer sb = new StringBuffer();
		sb.append(" ID: " + info.getId());
		sb.append(" ���ߺ�: " + info.getAntennaNo());
		sb.append(" �豸��: " + info.getDeviceNo());
		sb.append(" RSSI(�ź�ǿ��): " + info.getRssi());
		sb.append(" IP�򴮿ں� ��" + info.getCommunicationMode());
		
		//��ͬ�ı�ǩ��˽�����ݶ���Ҳ��ͬ
		sb.append(" ˽������: " + info.getPrivateData()); 
		
		//�¶�ֵ��Щ��ǩ���ܲ���ʪ��ֵ����������Ӧ��ֵ����Ч��
		sb.append(" �¶�ֵ �� " + info.getTemperature());	
		
		//ʱ��ص�ǰ�ص����ݵ�ʱ��
//		sb.append(" ʱ��� �� " + info.getTimeStamp());		
		
		//��ǩ���Ͳο�Java��UI��Demo
		sb.append(" ��ǩ���� �� " + info.getTagType());		
		
		//�û��Զ�������,���������豸���豸���ͺ�
//		sb.append(" �û����� �� " + info.getUserData());	
		
		//��ͬ�ı�ǩ������ֵ����Ҳ��ͬ
		sb.append(" ����ֵ: " + info.getAlarm());			
		System.out.println(sb.toString());
	}
	
	@Override
	public void GPSInfo(GPS_DataInfo GPSInfo) { //���豸����GPS��Ӳ���Ż�����ص�����
		//GPSԭʼ����
		String originalData = GPSInfo.getOriginalData();  
		
		 //�豸��
		String deviceNo = GPSInfo.getDeviceNo();    
		
		//GPS��������
		EnumGPSType type = GPSInfo.getType();			
		
		//GPGGA����
		GPGGA GPGGAType = GPSInfo.getGPGGAType();			
		
		//1.UTC ʱ�䣬hhmmss��ʱ���룩��ʽ
		String time = GPSInfo.getGPGGAType().getTime();		
		
		//2.��λ״̬ A����Ч��λ V����Ч��λ
		boolean positioningState = GPSInfo.getGPGGAType().isPositioningState();	  
		
		//3.γ�� ddmm.mmmm���ȷ֣���ʽ��ǰ��� 0 Ҳ�������䣩
		String latitude = GPSInfo.getGPGGAType().getLatitude();						
		
		//4.γ�ȷ��� N����γ S����γ
		String latitudeDirection = GPSInfo.getGPGGAType().getLatitudeDirection();   
		
		//5.���� dddmm.mmmm���ȷ֣���ʽ��ǰ��� 0 Ҳ�������䣩
		String longitude = GPSInfo.getGPGGAType().getLongitude();					
		
		//6.���ȷ��� E������ W������
		String longitudeDirection = GPSInfo.getGPGGAType().getLongitudeDirection(); 
		
		//7.�������ʣ�000.0~999.9 �ڣ�ǰ��� 0 Ҳ�������䣩
		String groundSpeed = GPSInfo.getGPGGAType().getGroundSpeed();				
		
		//8.���溽��000.0~359.9 �ȣ����汱Ϊ�ο���׼��ǰ��� 0 Ҳ�������䣩
		String groundSailingDirections = GPSInfo.getGPGGAType().getGroundSailingDirections();
		
		//9.UTC ���ڣ�ddmmyy�������꣩��ʽ
		String date = GPSInfo.getGPGGAType().getDate();			
		
		//10.��ƫ�ǣ�000.0~180.0 �ȣ�ǰ��� 0 Ҳ�������䣩
		String magneticDeclination = GPSInfo.getGPGGAType().getMagneticDeclination(); 
		
		//11.��ƫ�Ƿ��� E���� W����
		String magneticDeclinationDirections = GPSInfo.getGPGGAType().getMagneticDeclinationDirections();
		
		//ģʽָʾ A��������λ D����� E������ N��������Ч
		String patternInstructions = GPSInfo.getGPGGAType().getPatternInstructions(); 
		
		StringBuffer sb = new StringBuffer();
		sb.append(" GPSԭʼ����: ");
		sb.append(originalData);
		sb.append(" �豸��: ");
		sb.append(deviceNo);
		sb.append(" GPS��������: ");
		sb.append(type);
		sb.append(" GPGGA����: ");
		sb.append(GPGGAType);
		sb.append(" UTC ʱ�䣬hhmmss��ʱ���룩��ʽ: ");
		sb.append(time);
		sb.append(" ��λ״̬ A����Ч��λ V����Ч��λ: ");
		sb.append(positioningState);
		sb.append(" γ�� ddmm.mmmm���ȷ֣���ʽ��ǰ��� 0 Ҳ�������䣩: ");
		sb.append(latitude);
		sb.append(" γ�ȷ��� N����γ S����γ: ");
		sb.append(latitudeDirection);
		sb.append(" ���� dddmm.mmmm���ȷ֣���ʽ��ǰ��� 0 Ҳ�������䣩: ");
		sb.append(longitude);
		sb.append(" ���ȷ��� E������ W������: ");
		sb.append(longitudeDirection);
		sb.append(" �������ʣ�000.0~999.9 �ڣ�ǰ��� 0 Ҳ�������䣩: ");
		sb.append(groundSpeed);
		sb.append(" ���溽��000.0~359.9 �ȣ����汱Ϊ�ο���׼��ǰ��� 0 Ҳ�������䣩: ");
		sb.append(groundSailingDirections);
		sb.append(" UTC ���ڣ�ddmmyy�������꣩��ʽ: ");
		sb.append(date);
		sb.append(" ��ƫ�ǣ�000.0~180.0 �ȣ�ǰ��� 0 Ҳ�������䣩: ");
		sb.append(magneticDeclination);
		sb.append(" ��ƫ�Ƿ��� E���� W����: ");
		sb.append(magneticDeclinationDirections);
		sb.append(" ģʽָʾ A��������λ D����� E������ N��������Ч: ");
		sb.append(patternInstructions);
		System.out.println(sb.toString());
	}

//	@Override
//	public void heartBeat(String deviceNo,String deviceVersion,String readCardStatus,String stateTrigger,String readHeadState,long timeStamp,String communicationMode) {
//		StringBuffer sb = new StringBuffer();
//		sb.append(" �豸��: " + deviceNo);
//		
//		sb.append(" �豸�汾��: " + deviceVersion);
//		
//		//�豸���ڶ���״̬������ʱ01��,00���ʾ��ֹͣ��
//		sb.append(" ����״̬: " + readCardStatus);
//		
//		//����״̬��02��01��ʾ��⵽������00�޴���
//		sb.append(" ����״̬: " + stateTrigger);
//		
//		//��ͷ����״̬��00������01���ֹ���ʱ��02���ֹ�ֹͣʧ��
//		sb.append(" ��ͷ����״̬: " + readHeadState);
//		
//		//ʱ��ص�ǰ�ص����ݵ�ʱ��
//		sb.append(" ʱ��� �� " + timeStamp);	
//		
//		sb.append(" IP�򴮿ں� ��" + communicationMode);
//		System.out.println(sb.toString());
//	}

	@Override
	public void onlyHighFrquency(OutputDataHighFrquency outputData) {
		//IP�򴮿ں�
		String communicationMode = outputData.getCommunicationMode();
		//���
		int block = outputData.getBlock();
		//�豸��
		String deviceNo = outputData.getDeviceNo();
		//������
		String blockData = outputData.getBlockData();
		StringBuffer sb = new StringBuffer();
		sb.append(" IP�򴮿ں�: ");
		sb.append(communicationMode);
		sb.append(" ���: ");
		sb.append(block);
		sb.append(" �豸��: ");
		sb.append(deviceNo);
		sb.append(" ������: ");
		sb.append(blockData);
		System.out.println(sb.toString());
	}

	@Override
	public void heartBeat(String deviceNo, String deviceVersion,String readCardStatus, String stateTrigger, String readHeadState,long timeStamp, String communicationMode) {
		// TODO Auto-generated method stub
	}
}