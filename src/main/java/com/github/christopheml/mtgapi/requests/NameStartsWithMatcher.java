package com.github.christopheml.mtgapi.requests;

import com.github.christopheml.mtgapi.entities.Card;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;

public class NameStartsWithMatcher extends NameMatcher {

    private final String namePrefix;

    public NameStartsWithMatcher(String namePrefix) {
        this.namePrefix = namePrefix.toLowerCase();
    }

    @Override
    public Optional<String> parameterValue() {
        return Optional.of("name");
    }

    @Override
    public Collection<Predicate<Card>> resultFilters() {
        return Collections.singletonList(card -> card.getName().toLowerCase().startsWith(namePrefix));
    }

}
