package com.github.christopheml.mtgapi.requests;

import com.github.christopheml.mtgapi.entities.Card;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;

public class NameEqualsToMatcher extends NameMatcher {

    private final String expectedName;

    public NameEqualsToMatcher(String expectedName) {
        this.expectedName = expectedName;
    }

    @Override
    public Optional<String> parameterValue() {
        return Optional.ofNullable(expectedName);
    }

    @Override
    public Collection<Predicate<Card>> resultFilters() {
        return Collections.singletonList(card -> expectedName.equalsIgnoreCase(card.getName()));
    }

}
