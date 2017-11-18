package com.github.christopheml.mtgapi.requests.name;

import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.requests.CardQueryParameter;

import java.util.Collection;
import java.util.function.Predicate;

import static java.util.Collections.singletonList;

public class NameEndsWith implements NameMatcher {

    private final String suffix;

    NameEndsWith(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public Collection<CardQueryParameter> parameters() {
        return singletonList(new CardQueryParameter("name", suffix));
    }

    @Override
    public Predicate<Card> resultFilter() {
        return card -> card.getName().toLowerCase().endsWith(suffix.toLowerCase());
    }

}
