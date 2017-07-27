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
public final class Application {

    private Application() {
    }

    public static ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static JsonDeserializer jsonDeserializer() {
        return new JsonDeserializer(objectMapper());
    }

    public static HttpClient httpClient() {
        CloseableHttpClient closeableHttpClient = HttpClients.createSystem();
        return new HttpClient(closeableHttpClient, jsonDeserializer());
    }

}
