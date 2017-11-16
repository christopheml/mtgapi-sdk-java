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

    /**
     * Creates a new Card API object with default options.
     */
    public CardApi() {
        this(CardApiOptions.options());
    }

    /**
     * Creates a new Card API object with the provided options.
     * @param options Card API options.
     */
    public CardApi(CardApiOptions options) {
        endpoint = options.getEndpoint();
        httpClient = CardApiFactory.httpClient(options);
    }

    /**
     * Returns the card with the corresponding multiverseId.
     * @param multiverseId multiverseId of the card
     * @return the Card with the provided multiverseId.
     */
    public Card findOne(long multiverseId) {
        String url = String.format("%s/%d", endpoint, multiverseId);
        CardResponse response = httpClient.get(url, CardResponse::new);
        return response.getEntity();
    }

    /**
     * Returns all the cards matching a specific query
     * @param query query object containing specific criteria for the search
     * @return a list of Cards matching the criteria
     */
    public List<Card> find(CardQuery query) {
        String url = query.toUrl(endpoint);
        CardsResponse response = httpClient.list(url, CardsResponse::new);
        return response.getEntities();
    }

}
