package com.rfid.test.Discrete.proxy;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rfid.test.Discrete.model.SerialSettings;

public class ControlServiceProxy {
    private static final String SETTING_ENDPOINT = "/setting";
    private static final String POST_ENDPOINT = "/post";

    private final String settingUrl;
    private final String postUrl;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ControlServiceProxy(final String baseUrl, final HttpClient httpClient, final ObjectMapper objectMapper) {
        this.settingUrl = baseUrl + SETTING_ENDPOINT;
        this.postUrl = baseUrl + POST_ENDPOINT;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public SerialSettings getSerailSettings() throws Exception {
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
}
