package com.github.christopheml.mtgapi.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.christopheml.mtgapi.responses.ApiResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
     * @param responseClass Concrete class of the object to map to
     * @return A object of the given class representing the entity in the HTTP response
     */
    public ApiResponse get(String path, Class<? extends ApiResponse> responseClass) {
        HttpGet request = new HttpGet(path);

        logger.info("HTTP GET request to {}", path);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return processApiResponse(responseClass, response);
        } catch (IOException e) {
            throw new HttpRequestFailedException(e);
        }
    }

    private ApiResponse processApiResponse(Class<? extends ApiResponse> responseClass, HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        ApiResponse apiResponse = objectMapper.readValue(entity.getContent(), responseClass);
        apiResponse.setCount(response);
        apiResponse.setPageSize(response);
        apiResponse.setTotalCount(response);
        apiResponse.setRatelimitRemaining(response);
        apiResponse.setLinks(response);
        return apiResponse;
    }

    public static JsonHttpClient defaultInstance() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new JsonHttpClient(HttpClients.createDefault(), objectMapper);
    }

}
