package com.github.christopheml.mtgapi.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.christopheml.mtgapi.entities.Card;

public class CardResponse extends ApiResponse {

    private final Card card;

    @JsonCreator
    public CardResponse(@JsonProperty("card") Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

}
