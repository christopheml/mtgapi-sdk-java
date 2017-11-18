package com.github.christopheml.mtgapi.requests.name;

import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.requests.CardQueryParameter;

import java.util.Collection;
import java.util.function.Predicate;

import static java.util.Collections.singletonList;

public class NameEndsWithMatcher implements NameMatcher {

    private final String suffix;

    public NameEndsWithMatcher(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public Collection<CardQueryParameter> parameters() {
        return singletonList(new CardQueryParameter("name", suffix));
    }

    @Override
    public Collection<Predicate<Card>> resultFilters() {
        return singletonList(card -> card.getName().toLowerCase().endsWith(suffix.toLowerCase()));
    }

}
