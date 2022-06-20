package com.rfid.util;

import java.net.InetAddress;
import java.util.Properties;

public class SystemType {
	
    public static int config() {
        try {
        	
            InetAddress addr = InetAddress.getLocalHost();
            String ip = NetworkConfig.getLocalHostLANAddress().getHostAddress();
            String hostName = addr.getHostName().toString(); 
            System.out.println("Local IP: " + ip);
            System.out.println("Local Name: " + hostName);
            Properties props = System.getProperties();
            String name = props.getProperty("os.name");
            System.out.println("Operation System name: " + name);
            System.out.println("Operation System Version: " + props.getProperty("os.version"));
            if(name.equals("Linux")){
            	return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
