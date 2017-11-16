package com.github.christopheml.mtgapi.requests;

import com.github.christopheml.mtgapi.entities.Card;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Represents a Card API predicate.
 */
public interface CardMatcher {

    /**
     * Provides the query parameters for the REST API.
     * @return query parameters to append to the query.
     */
    Collection<CardQueryParameter> parameters();

    /**
     * Provides the result filters applied to query results.
     * @return result filters to apply to query results for this predicate.
     */
    Collection<Predicate<Card>> resultFilters();

}
