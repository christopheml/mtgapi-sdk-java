package com.github.christopheml.mtgapi.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.christopheml.mtgapi.responses.ApiResponse;
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
     * @return A object of the given class representing the entity in the HTTP response
     */
    public ApiResponse get(String path, Class<? extends ApiResponse> objectClass) {
        HttpGet request = new HttpGet(path);

        logger.info("HTTP GET request to {}", path);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            // TODO Handle rate related header
            HttpEntity entity = response.getEntity();
            return objectMapper.readValue(entity.getContent(), objectClass);
        } catch (IOException e) {
            throw new HttpRequestFailedException(e);
        }
    }

    public static JsonHttpClient defaultInstance() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new JsonHttpClient(HttpClients.createDefault(), objectMapper);
    }

}
