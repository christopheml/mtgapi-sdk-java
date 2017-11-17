package com.github.christopheml.mtgapi.requests;

import com.github.christopheml.mtgapi.entities.Card;

import java.util.Collection;
import java.util.function.Predicate;

import static java.util.Collections.singletonList;

public class NameStartsWithMatcher implements NameMatcher {

    private final String namePrefix;

    public NameStartsWithMatcher(String namePrefix) {
        this.namePrefix = namePrefix.toLowerCase();
    }

    @Override
    public Collection<CardQueryParameter> parameters() {
        return singletonList(new CardQueryParameter("name", namePrefix));
    }

    @Override
    public Collection<Predicate<Card>> resultFilters() {
        return singletonList(card -> card.getName().toLowerCase().startsWith(namePrefix));
    }

}
