package com.github.christopheml.mtgapi;

import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.http.HttpClient;
import com.github.christopheml.mtgapi.responses.CardResponse;

public class CardApi {

    private final String endpoint;

    private final HttpClient httpClient;

    public CardApi(String endpoint, HttpClient httpClient) {
        this.endpoint = endpoint;
        this.httpClient = httpClient;
    }

    public Card singleCard(long multiverseId) {
        String url = String.format("%s/%d", endpoint, multiverseId);
        CardResponse response = httpClient.get(url, CardResponse::new);
        return response.getEntity();
    }

}
