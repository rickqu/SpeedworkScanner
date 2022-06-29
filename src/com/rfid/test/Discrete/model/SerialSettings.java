package com.rfid.test.Discrete.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record SerialSettings(String port, int BaudRate) { }