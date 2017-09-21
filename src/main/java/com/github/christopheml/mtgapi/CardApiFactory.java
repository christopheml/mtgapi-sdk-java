package com.github.christopheml.mtgapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.christopheml.mtgapi.http.HttpClient;
import com.github.christopheml.mtgapi.json.JsonDeserializer;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Handles dependency instanciation and injection for the library.
 */
public final class CardApiFactory {

    private CardApiFactory() {
    }

    public static ObjectMapper objectMapper(CardApiOptions options) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static JsonDeserializer jsonDeserializer(CardApiOptions options) {
        return new JsonDeserializer(objectMapper(options));
    }

    public static HttpClient httpClient(CardApiOptions options) {
        CloseableHttpClient closeableHttpClient = HttpClients.createSystem();
        return new HttpClient(closeableHttpClient, jsonDeserializer(options));
    }

}
