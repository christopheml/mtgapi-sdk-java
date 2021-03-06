package com.github.christopheml.mtgapi.requests.name;

import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.requests.CardQueryParameter;

import java.util.Collection;
import java.util.function.Predicate;

import static java.util.Collections.singletonList;

public class NameStartsWith implements NameMatcher {

    private final String namePrefix;

    NameStartsWith(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @Override
    public Collection<CardQueryParameter> parameters() {
        return singletonList(new CardQueryParameter("name", namePrefix));
    }

    @Override
    public Predicate<Card> resultFilter() {
        return card -> card.getName().toLowerCase().startsWith(namePrefix.toLowerCase());
    }

}
