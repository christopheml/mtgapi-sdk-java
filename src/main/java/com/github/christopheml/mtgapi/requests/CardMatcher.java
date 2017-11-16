package com.github.christopheml.mtgapi.requests;

import com.github.christopheml.mtgapi.entities.Card;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Represents a Card API predicate.
 */
public interface CardMatcher {

    /**
     * Provides the value of the API parameter for this predicate, if any.
     */
    Optional<String> parameterValue();

    /**
     * Provides the resultFilters applied to query results.
     * @return resultFilters to apply to query results for this predicate.
     */
    Collection<Predicate<Card>> resultFilters();

    void appendParameter(Map<String, String> parameters);
}
