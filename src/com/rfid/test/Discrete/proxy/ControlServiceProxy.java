package com.rfid.test.Discrete.proxy;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rfid.test.Discrete.model.RFIDScanResult;
import com.rfid.test.Discrete.model.SerialSettings;

public class ControlServiceProxy {
    private static final String SETTING_ENDPOINT = "/settings";
    private static final String RESULT_ENDPOINT = "/readresult";

    private final String settingUrl;
    private final String resultUrl;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ControlServiceProxy(final String baseUrl, final HttpClient httpClient, final ObjectMapper objectMapper) {
        this.settingUrl = baseUrl + SETTING_ENDPOINT;
        this.resultUrl = baseUrl + RESULT_ENDPOINT;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public SerialSettings getSerialSettings() throws Exception {
        try {
            HttpRequest settingsRequest = HttpRequest.newBuilder()
            .uri(new URI(settingUrl))
            .GET()
            .build();

            HttpResponse<String> response = httpClient.send(settingsRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), SerialSettings.class);
        } catch (final Exception e) {
            System.err.println("An exception was encountered whilst getting settings: " + e);
            throw e;
        }
    }

    public int postResult(final RFIDScanResult rfidScanResult) throws Exception {
        try {
            HttpRequest settingsRequest = HttpRequest.newBuilder()
            .uri(new URI(resultUrl))
            .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(rfidScanResult)))
            .build();

            HttpResponse<String> response = httpClient.send(settingsRequest, HttpResponse.BodyHandlers.ofString());
            return response.statusCode();
        } catch (final Exception e) {
            System.err.println("An exception was encountered whilst getting settings: " + e);
            throw e;
        }
    }
}
