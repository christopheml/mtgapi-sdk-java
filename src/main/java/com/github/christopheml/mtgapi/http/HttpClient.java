package com.github.christopheml.mtgapi.http;

import com.github.christopheml.mtgapi.json.JsonDeserializer;
import com.github.christopheml.mtgapi.responses.SingleResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Supplier;

public final class HttpClient {

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

    private final CloseableHttpClient httpClient;

    private final JsonDeserializer jsonDeserializer;

    public HttpClient(CloseableHttpClient httpClient, JsonDeserializer jsonDeserializer) {
        this.httpClient = httpClient;
        this.jsonDeserializer = jsonDeserializer;
    }

    /**
     * Queries an URL to retrieve a single entity in JSON form and binds it to an object.
     * @param path URL to query
     * @param responseSupplier Supplier providing an instance of the response class
     * @return A object of the given class representing the entity in the HTTP response
     */
    public <ENTITY, T extends SingleResponse<ENTITY>> T get(String path, Supplier<T> responseSupplier) {
        HttpGet request = new HttpGet(path);

        logger.info("HTTP GET request to {}", path);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return processApiResponse(responseSupplier, response);
        } catch (IOException e) {
            throw new HttpRequestFailedException(e);
        }
    }

    private <ENTITY, T extends SingleResponse<ENTITY>> T processApiResponse(Supplier<T> responseSupplier, HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        T apiResponse = jsonDeserializer.deserialize(entity.getContent(), responseSupplier);
        apiResponse.setCount(response);
        apiResponse.setPageSize(response);
        apiResponse.setTotalCount(response);
        apiResponse.setRatelimitRemaining(response);
        apiResponse.setLinks(response);
        return apiResponse;
    }

}
