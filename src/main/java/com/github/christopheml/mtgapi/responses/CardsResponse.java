package com.github.christopheml.mtgapi.responses;

import com.github.christopheml.mtgapi.entities.Card;

public class CardsResponse extends ListResponse<Card> {

    @Override
    public Class<Card> getEntityClass() {
        return Card.class;
    }

    @Override
    public String getEntityRoot() {
        return "cards";
    }

}
