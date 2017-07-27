package com.github.christopheml.mtgapi;

import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.http.HttpClient;
import com.github.christopheml.mtgapi.requests.CardQuery;
import com.github.christopheml.mtgapi.responses.CardResponse;
import com.github.christopheml.mtgapi.responses.CardsResponse;

import java.util.List;

public class CardApi {

    private final String endpoint;

    private final HttpClient httpClient;

    public CardApi(String endpoint, HttpClient httpClient) {
        this.endpoint = endpoint;
        this.httpClient = httpClient;
    }

    public Card findOne(long multiverseId) {
        String url = String.format("%s/%d", endpoint, multiverseId);
        CardResponse response = httpClient.get(url, CardResponse::new);
        return response.getEntity();
    }

    public List<Card> find(CardQuery query) {
        String url = query.toUrl(endpoint);
        CardsResponse response = httpClient.list(url, CardsResponse::new);
        return response.getEntities();
    }

}
