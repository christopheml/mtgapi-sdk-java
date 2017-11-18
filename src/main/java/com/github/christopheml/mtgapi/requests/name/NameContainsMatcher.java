package com.github.christopheml.mtgapi.requests.name;

import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.requests.CardQueryParameter;

import java.util.Collection;
import java.util.function.Predicate;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * Matches cards whose name contains a given text.
 */
public class NameContainsMatcher implements NameMatcher {

    private final String fragment;

    public NameContainsMatcher(String fragment) {
        this.fragment = fragment;
    }

    @Override
    public Collection<CardQueryParameter> parameters() {
        return singletonList(new CardQueryParameter("name", fragment));
    }

    @Override
    public Collection<Predicate<Card>> resultFilters() {
        return emptyList();
    }

}
