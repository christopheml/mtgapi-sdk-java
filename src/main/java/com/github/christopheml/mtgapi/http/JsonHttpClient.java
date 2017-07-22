package com.github.christopheml.mtgapi.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public final class JsonHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(JsonHttpClient.class);

    private final CloseableHttpClient httpClient;

    private final ObjectMapper objectMapper;

    public JsonHttpClient(CloseableHttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    /**
     * Queries an URL to retrieve a single entity in JSON form and binds it to an object.
     * @param path URL to query
     * @param objectClass Concrete class of the object to map to
     * @param <T> Type of the concrete class
     * @return A object of the given class representing the entity in the HTTP response
     * @throws IOException when the HTTP request fails
     */
    public <T> T get(String path, Class<T> objectClass) throws IOException {
        HttpGet request = new HttpGet(path);

        logger.info("HTTP GET request to {}", path);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            // TODO Handle rate related header
            HttpEntity entity = response.getEntity();
            return objectMapper.readValue(entity.getContent(), objectClass);
        }
    }

    public static JsonHttpClient defaultInstance() {
        return new JsonHttpClient(HttpClients.createDefault(), new ObjectMapper());
    }

}
