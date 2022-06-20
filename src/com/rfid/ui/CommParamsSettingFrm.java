package com.rfid.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.rfid.util.DataConvert;
import com.rfid.util.NetworkConfig;
import com.rfid.util.ReaderUtil;
import com.rfid.util.Regex;
import com.rfid.util.ZLTool;

public class CommParamsSettingFrm extends JDialog implements ActionListener,ListSelectionListener{
	private static final long serialVersionUID = 1L;
	private JTextField tfIPAddress;
	private JTextField tfSubnetMask;
	private JTextField tfPort;
	private JTextField tfGateway;
	private JTextField tfDestinationIP;
	private JTextField tfDestinationPort;
	//private JTabbedPane tabbedPane;
	private JPanel panel_showIPInfoTable;
	private JLabel lblNetworkMode;
	private JLabel lblIPMode;
	private JLabel lblIPAddress;
	private JLabel lblSubnetMask;
	private JLabel lblPort;
	private JLabel lblGateway;
	private JButton btnSet;
	private JButton btnR2000Default;
	private JButton btn2_4gDefault;
	private JButton btnSearchDevice;
	private JPanel panel_content;
	private JPanel panel_networkParams;
	private JComboBox<String> cbbNetworkMode;
	private JComboBox<String> cbbIPMode;
	private JPanel panel_serialPortParams;
	private JLabel lblBaudRate;
	private JLabel lblDataBits;
	private JComboBox<String> cbbBaudRate;
	private JLabel lblParity;
	private JComboBox<String> cbbParity;
	private JComboBox<String> cbbDataBits;
	private JLabel lblDestinationPort;
	private JLabel lblDestinationIP;
	private JLabel lblPromotion;
	public JScrollPane sp_showIPInfo;
	public JTable table_ZL;
	public DefaultTableModel ZLtableModel;
	private JTextField tfLocalHostIP;
	private JButton btnLocalHostRead;
	private JLabel lblLocalHost;
	private static String title = ReaderUtil.language.getString("dialogTitleCommParamsSetting");
	private JButton btnDiscreteDefault;
	private JButton btnFinish;
	private JLabel lblMessage;
	
	public CommParamsSettingFrm(JFrame frame) {
		super(frame,title,true);
		this.setSize(760, 520);
		this.setLocationRelativeTo(null);
		inital();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void main(String []args){
		new CommParamsSettingFrm(new JFrame());
	}
	
	private void networkParams() {
		panel_networkParams = new JPanel();
		String title = ReaderUtil.language.getString("titleBorderNetworkParams");
		TitledBorder titledBorder = new TitledBorder(title);
		titledBorder.setTitleJustification(TitledBorder.LEADING);
		titledBorder.setTitlePosition(TitledBorder.TOP);
		panel_networkParams.setBorder(titledBorder);
		panel_networkParams.setBounds(437, 21, 303, 349);
		panel_content.add(panel_networkParams);
		panel_networkParams.setLayout(null);
		networkParamsConfig();
		tcpClientConfig();
	}

	private void networkParamsConfig() {
		networkParamsConfigTitle();
		networkParamsConfigContent();
		networkParamsConfigAddPanel();
	}
	
	private void networkParamsConfigContent() {
		cbbNetworkMode = new JComboBox<String>();
		cbbNetworkMode.setModel(new DefaultComboBoxModel<String>(new String[] {"TCP Server", "TCP Client", "UDP", "UDP Group"}));
		cbbNetworkMode.setBounds(146, 27, 132, 21);
		
		cbbIPMode = new JComboBox<String>();
		cbbIPMode.setModel(new DefaultComboBoxModel<String>(new String[] {"Static", "Dynamic"}));
		cbbIPMode.setBounds(146, 63, 132, 21);
		
		tfIPAddress = new JTextField();
		tfIPAddress.setBounds(146, 99, 132, 21);
		tfIPAddress.setColumns(10);
		
		tfSubnetMask = new JTextField();
		tfSubnetMask.setColumns(10);
		tfSubnetMask.setBounds(146, 135, 132, 21);
		
		tfPort = new JTextField();
		tfPort.setColumns(10);
		tfPort.setBounds(146, 171, 132, 21);
		
		tfGateway = new JTextField();
		tfGateway.setColumns(10);
		tfGateway.setBounds(146, 207, 132, 21);
		
		tfDestinationIP = new JTextField();
		tfDestinationIP.setColumns(10);
		tfDestinationIP.setBounds(146, 279, 132, 21);
		
		tfDestinationPort = new JTextField();
		tfDestinationPort.setColumns(10);
		tfDestinationPort.setBounds(146, 315, 132, 21);
	}

	private void networkParamsConfigTitle() {
		lblNetworkMode = new JLabel(ReaderUtil.language.getString("lblNetworkMode"));
		lblNetworkMode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNetworkMode.setBounds(30, 27, 100, 21);
		
		lblIPMode = new JLabel(ReaderUtil.language.getString("lblIPMode"));
		lblIPMode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIPMode.setBounds(30, 63, 100, 21);
		
		lblIPAddress = new JLabel(ReaderUtil.language.getString("lblIPAddress"));
		lblIPAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIPAddress.setBounds(30, 99, 100, 21);
		
		lblSubnetMask = new JLabel(ReaderUtil.language.getString("lblSubnetMask"));
		lblSubnetMask.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubnetMask.setBounds(30, 135, 100, 21);
		
		lblPort = new JLabel(ReaderUtil.language.getString("lblPort"));
		lblPort.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPort.setBounds(30, 171, 100, 21);
		
		lblGateway = new JLabel(ReaderUtil.language.getString("lblGateway"));
		lblGateway.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGateway.setBounds(30, 207, 100, 21);
	}

	private void networkParamsConfigAddPanel() {
		panel_networkParams.add(lblNetworkMode);
		panel_networkParams.add(lblIPMode);
		panel_networkParams.add(lblIPAddress);
		panel_networkParams.add(lblSubnetMask);
		panel_networkParams.add(lblPort);
		panel_networkParams.add(lblGateway);
		panel_networkParams.add(cbbNetworkMode);
		panel_networkParams.add(cbbIPMode);
		panel_networkParams.add(tfIPAddress);
		panel_networkParams.add(tfSubnetMask);
		panel_networkParams.add(tfPort);
		panel_networkParams.add(tfGateway);
		panel_networkParams.add(tfDestinationIP);
		panel_networkParams.add(tfDestinationPort);
	}

	private void tcpClientConfig() {
		lblPromotion = new JLabel(ReaderUtil.language.getString("lblBelowSettingOnlyUseInTCPClient"));
		lblPromotion.setBounds(31, 251, 248, 15);
		
		lblDestinationIP = new JLabel(ReaderUtil.language.getString("lblDestinationIP"));
		lblDestinationIP.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDestinationIP.setBounds(30, 279, 100, 21);
		
		lblDestinationPort = new JLabel(ReaderUtil.language.getString("lblDestinationPort"));
		lblDestinationPort.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDestinationPort.setBounds(30, 315, 100, 21);
		
		panel_networkParams.add(lblPromotion);
		panel_networkParams.add(lblDestinationIP);
		panel_networkParams.add(lblDestinationPort);
	}

	
	
	private void showIPInfoTable() {
		panel_showIPInfoTable = new JPanel();
		panel_showIPInfoTable.setBounds(10, 21, 417, 217);
		panel_content.add(panel_showIPInfoTable);
		panel_showIPInfoTable.setLayout(new GridLayout(1, 0, 0, 0));
		
		String[] ZLcolumnHeader = {ReaderUtil.language.getString("tableTitleZlNo"),
				ReaderUtil.language.getString("tableTitleZlIP"),
				ReaderUtil.language.getString("tableTitleZlPort"), 
				ReaderUtil.language.getString("tableTitleZlMAC")};
		
		ZLtableModel = new DefaultTableModel(null, ZLcolumnHeader){  
			
			private static final long serialVersionUID = 1L;
			
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            }  
        };  
		
		sp_showIPInfo = new JScrollPane();
		panel_showIPInfoTable.add(sp_showIPInfo);
		table_ZL = new JTable(ZLtableModel);
		CommTable.setTableStyle(table_ZL);
		table_ZL.getSelectionModel().addListSelectionListener(this);
		sp_showIPInfo.setViewportView(table_ZL);
		btnSearchDevice = new JButton(ReaderUtil.language.getString("btnSearchDevice"));
		btnSearchDevice.setBounds(291, 246, 135, 23);
		panel_content.add(btnSearchDevice);
	}

	private void serialPortParams() {
		panel_serialPortParams = new JPanel();
		
		String title = ReaderUtil.language.getString("titleBorderSerialPortParams");
		TitledBorder titledBorder = new TitledBorder(title);
		titledBorder.setTitleJustification(TitledBorder.LEADING);
		titledBorder.setTitlePosition(TitledBorder.TOP);
		panel_serialPortParams.setBorder(titledBorder);
		
		panel_serialPortParams.setBounds(10, 271, 417, 99);
		panel_serialPortParams.setLayout(null);
		lblBaudRate = new JLabel(ReaderUtil.language.getString("lblBaudate"));
		lblBaudRate.setBounds(24, 25, 65, 15);
		lblDataBits = new JLabel(ReaderUtil.language.getString("lblDataBits"));
		lblDataBits.setBounds(24, 63, 65, 15);
		
		cbbBaudRate = new JComboBox<String>();
		cbbBaudRate.setModel(new DefaultComboBoxModel<String>(new String[] { "1200", "2400", "4800", "7200", "9600", "14400",
				"19200", "28800", "38400", "57600", "76800", "115200", "230400" }));
		cbbBaudRate.setBounds(113, 25, 90, 21);
		lblParity = new JLabel(ReaderUtil.language.getString("lblParity"));
		
		lblParity.setBounds(227, 25, 52, 15);
		
		cbbParity = new JComboBox<String>();
		cbbParity.setBounds(303, 25, 90, 21);
		cbbParity.setModel(new DefaultComboBoxModel<String>(new String[] { "Node", "Odd", "Even", "Marked", "Space" }));
		
		cbbDataBits = new JComboBox<String>();
		cbbDataBits.setBounds(113, 60, 90, 21);
		cbbDataBits.setModel(new DefaultComboBoxModel<String>(new String[] { "8", "7", "6", "5" }));
		serialPortParamsAddPanel();
	}

	private void serialPortParamsAddPanel() {
		panel_content.add(panel_serialPortParams);
		panel_serialPortParams.add(lblBaudRate);
		panel_serialPortParams.add(lblDataBits);
		panel_serialPortParams.add(cbbBaudRate);
		panel_serialPortParams.add(lblParity);
		panel_serialPortParams.add(cbbParity);
		panel_serialPortParams.add(cbbDataBits);
	}

	private void inital() {
		panel_content = new JPanel();
		getContentPane().setLayout(new BorderLayout(0, 0));
		panel_content.setBorder(null);
		getContentPane().add(panel_content);
		panel_content.setLayout(null);
		
		networkParams();
		serialPortParams();
		showIPInfoTable();
		buttonGroup();
		
		btnSearchDevice.addActionListener(this);
		btnR2000Default.addActionListener(this);
		btnDiscreteDefault.addActionListener(this);
		btn2_4gDefault.addActionListener(this);
		btnSet.addActionListener(this);
		btnFinish.addActionListener(this);
		//btnSearchDevice.doClick();
	}
	
	private void buttonGroup() {
		btnR2000Default = new JButton(ReaderUtil.language.getString("btnR2000DefaultParams"));
		btnR2000Default.setBounds(10, 383, 150, 23);
		btnSet = new JButton(ReaderUtil.language.getString("btnSetParams"));
		btnSet.setBounds(437, 426, 130, 23);
		panel_content.add(btnR2000Default);
		panel_content.add(btnSet);
		
		lblLocalHost = new JLabel(ReaderUtil.language.getString("lblLocalHost"));
		lblLocalHost.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocalHost.setBounds(437, 383, 81, 18);
		panel_content.add(lblLocalHost);
		
		tfLocalHostIP = new JTextField();
		tfLocalHostIP.setBounds(531, 382, 106, 21);
		panel_content.add(tfLocalHostIP);
		tfLocalHostIP.setColumns(10);
		
		btnLocalHostRead = new JButton(ReaderUtil.language.getString("btnLocalHostRead"));
		btnLocalHostRead.addActionListener(this);
		btnLocalHostRead.setBounds(647, 380, 93, 23);
		panel_content.add(btnLocalHostRead);
		
		btnDiscreteDefault = new JButton(ReaderUtil.language.getString("btnDiscreteDefaultParams"));
		btnDiscreteDefault.setBounds(277, 380, 150, 23);
		panel_content.add(btnDiscreteDefault);
		
		btn2_4gDefault = new JButton(ReaderUtil.language.getString("btn2.4gDefaultParams"));
		btn2_4gDefault.setBounds(10, 426, 150, 23);
		panel_content.add(btn2_4gDefault);
		
		btnFinish = new JButton(ReaderUtil.language.getString("btnFileExportFinish"));
		btnFinish.setBounds(610, 426, 130, 23);
		panel_content.add(btnFinish);
		
		lblMessage = new JLabel();
		lblMessage.setForeground(Color.BLUE);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(10, 459, 730, 23);
		panel_content.add(lblMessage);
		
//		btnLocalHostRead.setVisible(false);
//		lblLocalHost.setVisible(false);
//		tfLocalHostIP.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if(object == btnSearchDevice){
			searchDevice();
		}else if(object == btnR2000Default){
			defaultR2000Network();
		}else if(object == btnDiscreteDefault){
			defaultDiscreteNetwork();
		}else if(object == btn2_4gDefault){
			default2Point4gNetwork();
		}else if(object == btnSet){
			setNetwork();
		}else if(object == btnLocalHostRead){
			queryLocalHostIP();
		}else if(object == btnFinish){
			dispose();
		}
	}
	
	private void queryLocalHostIP() {
		try {
			String localHost = NetworkConfig.getLocalHostLANAddress().getHostAddress();
			tfLocalHostIP.setText(localHost);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setNetwork() {
		try {
			String IPAddress = tfIPAddress.getText().trim();
			if (!Regex.isValidIP(IPAddress)) {
				lblMessage.setText(ReaderUtil.language.getString("msgInvalidIP"));
				return;
			}
			String subnetMask = tfSubnetMask.getText().trim();
			if (!Regex.isValidIP(subnetMask)) {
				lblMessage.setText(ReaderUtil.language.getString("msgInvalidMask"));
				return;
			}
			String gateWay = tfGateway.getText().trim();
			if (!Regex.isValidIP(gateWay)) {
				lblMessage.setText(ReaderUtil.language.getString("msgInvalidGateway"));
				return;
			}
			String destinationIP = tfDestinationIP.getText().trim();
			if (!Regex.isValidIP(destinationIP)) {
				lblMessage.setText(ReaderUtil.language.getString("msgInvalidDestIP"));
				return;
			}
			int portNo = Integer.parseInt((tfPort.getText().trim()));
			if (portNo < 1000 || portNo > 65535) {
				lblMessage.setText(ReaderUtil.language.getString("msgInvalidPort"));
				return;
			}
			int destinationPort = Integer.parseInt((tfDestinationPort.getText().trim()));
			if (destinationPort < 1000 || destinationPort > 65535) {
				lblMessage.setText(ReaderUtil.language.getString("msgInvalidDestPort"));
				return;
			}
			byte[] ip = new byte[32];
			byte[] netmask = new byte[32];
			byte[] gateway = new byte[32];
			byte[] destip = new byte[destinationIP.length()];
			
			ip = DataConvert.ipCutPointToByte(IPAddress);
			netmask = DataConvert.ipCutPointToByte(subnetMask);
			gateway = DataConvert.ipCutPointToByte(gateWay);
		    destip = DataConvert.ipCutPointToByte(destinationIP);
			byte deviceNo = ZLTool.selectedDevNo;
			byte workMode = (byte) cbbNetworkMode.getSelectedIndex();
			ZLTool.handler.setWorkMode(deviceNo, workMode);
			ZLTool.handler.setIPMode(deviceNo, (byte)cbbIPMode.getSelectedIndex());
			ZLTool.handler.setIP(deviceNo, ip);
			ZLTool.handler.setNetMask(deviceNo, netmask);
			ZLTool.handler.setGateWay(deviceNo, gateway);
			ZLTool.handler.setDestName(deviceNo, destip);
			ZLTool.handler.setPort(deviceNo, (short)portNo);
			ZLTool.handler.setDestPort(deviceNo, (short)destinationPort);
			ZLTool.handler.setBaudrateIndex(deviceNo, (byte) cbbBaudRate.getSelectedIndex());
			ZLTool.handler.setDataBits(deviceNo, (byte) cbbDataBits.getSelectedIndex());
			ZLTool.handler.setParity(deviceNo, (byte) cbbParity.getSelectedIndex());
			int row = table_ZL.getSelectedRow();
			if (row < 0) {
				lblMessage.setText(ReaderUtil.language.getString("msgPleaseSelectTheDeviceIP"));
				return;
			}
			boolean res = ZLTool.handler.setParam(deviceNo);
			if (res) {
				lblMessage.setText(ReaderUtil.language.getString("msgNetworkParamsSetSuccess"));
			} else {
				lblMessage.setText(ReaderUtil.language.getString("msgNetworkParamsSetFailed"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void default2Point4gNetwork(){
		default2Point4gNetworkParams();
	}
	
	private void defaultDiscreteNetwork(){
		defaultDiscreteNetworkParams();
	}
	
	private void defaultR2000Network() {
		defaultR2000NetworkParams();
	}

	private void searchDevice() {
		short devCount = ZLTool.handler.startSearchDev();
		((DefaultTableModel) table_ZL.getModel()).getDataVector().clear();
		((DefaultTableModel) table_ZL.getModel()).fireTableDataChanged();
		table_ZL.updateUI();
		for (byte i = 0; i < devCount; i++) {
			String IP = ZLTool.handler.getIP(i);
			int port = DataConvert.ReverseByte(ZLTool.handler.getPort(i));
			String devId = ZLTool.handler.getDevID(i);
			Object[] rowValues = { i + 1, IP, port,devId};
			ZLtableModel.addRow(rowValues);
		}
		if (devCount > 0) {
			byte nNum = (byte) (ZLTool.selectedDevNo);
			nNum = 0;
			updateCommParamControl(nNum);
			table_ZL.setRowSelectionInterval(nNum,nNum);
		}
	}
	
	public void defaultDiscreteNetworkParams(){
		cbbIPMode.setSelectedIndex(0);
		cbbNetworkMode.setSelectedIndex(0);
		tfIPAddress.setText("192.168.1.200");
		tfSubnetMask.setText("255.255.255.0");
		tfPort.setText("4196");
		tfGateway.setText("192.168.1.1");
		tfDestinationIP.setText("192.168.1.100");
		tfDestinationPort.setText("4196");
		cbbBaudRate.setSelectedIndex(4);
		cbbDataBits.setSelectedIndex(0);
		cbbParity.setSelectedIndex(0);
	}
	
	public void defaultR2000NetworkParams() {
		cbbIPMode.setSelectedIndex(0);
		cbbNetworkMode.setSelectedIndex(0);
		tfIPAddress.setText("192.168.1.200");
		tfSubnetMask.setText("255.255.255.0");
		tfPort.setText("20058");
		tfGateway.setText("192.168.1.1");
		tfDestinationIP.setText("192.168.1.100");
		tfDestinationPort.setText("20058");
		cbbBaudRate.setSelectedIndex(12);
		cbbDataBits.setSelectedIndex(0);
		cbbParity.setSelectedIndex(0);
	}
	
	public void default2Point4gNetworkParams(){
		cbbIPMode.setSelectedIndex(0);
		cbbNetworkMode.setSelectedIndex(0);
		tfIPAddress.setText("192.168.1.200");
		tfSubnetMask.setText("255.255.255.0");
		tfPort.setText("8000");
		tfGateway.setText("192.168.1.1");
		tfDestinationIP.setText("192.168.1.100");
		tfDestinationPort.setText("8000");
		cbbBaudRate.setSelectedIndex(12);
		cbbDataBits.setSelectedIndex(0);
		cbbParity.setSelectedIndex(0);
	}
	
	public void updateCommParamControl(byte nNum) {
		int workMode = ZLTool.handler.getWorkMode(nNum);
		cbbNetworkMode.setSelectedIndex(workMode);
		int IPMode = ZLTool.handler.getIPMode(nNum);
		cbbIPMode.setSelectedIndex(IPMode);
		String IP = ZLTool.handler.getIP(nNum);
		tfIPAddress.setText(IP);
		String netMask = String.valueOf(ZLTool.handler.getNetMask(nNum));
		tfSubnetMask.setText(netMask);
		int port = DataConvert.ReverseByte(ZLTool.handler.getPort(nNum));
		tfPort.setText(String.valueOf(port));
		String gateWay = ZLTool.handler.getGateWay(nNum);
		tfGateway.setText(gateWay);
		String destName = ZLTool.handler.getDestName(nNum);
		tfDestinationIP.setText(destName);
		int destPort = DataConvert.ReverseByte(ZLTool.handler.getDestPort(nNum));
		tfDestinationPort.setText(String.valueOf(destPort));
		int baudrateIndex = ZLTool.handler.getBaudrateIndex(nNum);
		cbbBaudRate.setSelectedIndex(baudrateIndex);
		int dataBits = ZLTool.handler.getDataBits(nNum);
		cbbDataBits.setSelectedIndex(dataBits);
		int parity = ZLTool.handler.getParity(nNum);
		cbbParity.setSelectedIndex(parity);
	}

	public void valueChanged(ListSelectionEvent e) {
		boolean falg = e.getValueIsAdjusting();
		int row = table_ZL.getSelectedRow();
		ZLTool.selectedDevNo = (byte) row;
		if (row > -1) {
			if (falg) {
				updateCommParamControl((byte)row);
			}
		}
	}
}
