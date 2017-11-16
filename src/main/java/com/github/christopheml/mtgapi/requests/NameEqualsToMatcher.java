package com.github.christopheml.mtgapi.requests;

import com.github.christopheml.mtgapi.entities.Card;

import java.util.Collection;
import java.util.function.Predicate;

import static java.util.Collections.singletonList;

public class NameEqualsToMatcher extends NameMatcher {

    private final String expectedName;

    public NameEqualsToMatcher(String expectedName) {
        this.expectedName = expectedName;
    }

    @Override
    public Collection<CardQueryParameter> parameters() {
        return singletonList(new CardQueryParameter("name", expectedName));
    }

    @Override
    public Collection<Predicate<Card>> resultFilters() {
        return singletonList(card -> expectedName.equalsIgnoreCase(card.getName()));
    }

}
