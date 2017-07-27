package com.github.christopheml.mtgapi.http;

import com.github.christopheml.mtgapi.json.JsonDeserializer;
import com.github.christopheml.mtgapi.responses.ApiResponse;
import com.github.christopheml.mtgapi.responses.ListResponse;
import com.github.christopheml.mtgapi.responses.SingleResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        logger.info("HTTP GET request for single entity to {}", path);
        T singleResponse = responseSupplier.get();

        doRequest(path, httpResponse -> {
            processHeaders(singleResponse, httpResponse);
            ENTITY entity = jsonDeserializer.deserialize(httpResponse.getEntity().getContent(), singleResponse);
            singleResponse.setEntity(entity);
        });

        return singleResponse;
    }

    public <ENTITY, T extends ListResponse<ENTITY>> T list(String path, Supplier<T> responseSupplier) {
        logger.info("HTTP GET request for multiple entities to {}", path);
        T listResponse = responseSupplier.get();

        List<ENTITY> entities = new ArrayList<>();

        do {
            doRequest(path, httpResponse -> {
                processHeaders(listResponse, httpResponse);
                entities.addAll(jsonDeserializer.deserialize(httpResponse.getEntity().getContent(), listResponse));
            });
        } while (listResponse.hasNext());

        listResponse.setEntities(entities);
        return listResponse;
    }

    private void doRequest(String path, ResponseHandler responseHandler) {
        HttpGet request = new HttpGet(path);

        try (CloseableHttpResponse httpResponse = httpClient.execute(request)) {
            responseHandler.apply(httpResponse);
        } catch (IOException e) {
            throw new HttpRequestFailedException(e);
        }
    }

    private <ENTITY, T extends ApiResponse<ENTITY>> void processHeaders(T singleResponse, HttpResponse response) {
        singleResponse.setCount(response);
        singleResponse.setPageSize(response);
        singleResponse.setTotalCount(response);
        singleResponse.setRatelimitRemaining(response);
        singleResponse.setLinks(response);
    }

}
