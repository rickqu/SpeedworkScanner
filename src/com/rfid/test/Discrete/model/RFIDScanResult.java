package com.rfid.test.Discrete.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record RFIDScanResult(String epc, long timestamp, String antennaNo, String deviceNo,String communicationMode, String temperature) {
    
}
