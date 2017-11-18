package com.github.christopheml.mtgapi.requests.name;

import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.requests.CardQueryParameter;

import java.util.Collection;
import java.util.function.Predicate;

import static java.util.Collections.singletonList;

public class NameEqualsTo implements NameMatcher {

    private final String expectedName;

    NameEqualsTo(String expectedName) {
        this.expectedName = expectedName;
    }

    @Override
    public Collection<CardQueryParameter> parameters() {
        return singletonList(new CardQueryParameter("name", expectedName));
    }

    @Override
    public Predicate<Card> resultFilter() {
        return card -> expectedName.equalsIgnoreCase(card.getName());
    }

}
