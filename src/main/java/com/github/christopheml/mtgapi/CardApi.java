package com.github.christopheml.mtgapi;

import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.http.JsonHttpClient;
import com.github.christopheml.mtgapi.responses.CardResponse;

public class CardApi {

    private final String endpoint;

    private final JsonHttpClient httpClient;

    public CardApi(String endpoint, JsonHttpClient httpClient) {
        this.endpoint = endpoint;
        this.httpClient = httpClient;
    }

    public Card singleCard(long multiverseId) {
        String url = String.format("%s/%d", endpoint, multiverseId);
        CardResponse response = (CardResponse) httpClient.get(url, CardResponse.class);
        return response.getCard();
    }

}
