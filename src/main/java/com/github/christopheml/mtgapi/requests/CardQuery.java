package com.github.christopheml.mtgapi.requests;

import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.requests.name.NameMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CardQuery {

    private final List<CardQueryParameter> parameters = new ArrayList<>();

    private final List<Predicate<Card>> resultFilters = new ArrayList<>();

    /**
     * Adds a predicate on the card name.
     *
     * <p>Card name predicates are <strong>case insensitive</strong>.</p>
     *
     * @param nameMatcher Matcher object applying the name predicate.
     * @return this query
     */
    public CardQuery name(NameMatcher nameMatcher) {
        registerMatcher(nameMatcher);
        return this;
    }

    private void registerMatcher(CardMatcher matcher) {
        parameters.addAll(matcher.parameters());
        resultFilters.addAll(matcher.resultFilters());
    }

    /**
     * Creates a new Card query.
     * @return an empty query
     */
    public static CardQuery query() {
        return new CardQuery();
    }

    /**
     * Creates the appropriate API URL for this query.
     *
     * @param endpoint Endpoint of the API
     * @return a {@code String} representation of the URL that represents this query.
     */
    public String toUrl(String endpoint) {
        String encodedParameters = parameters.stream()
                .map(p -> p.format("%s=%s"))
                .reduce((p1, p2) -> p1 + "&" + p2)
                .map(s -> "?" + s)
                .orElse("");
        return endpoint + encodedParameters;
    }

    /**
     * Filters a list of card using the parameters for this query.
     *
     * <p>This allows finer filtering than the API allows by applying additional resultFilters to the results returned by the server</p>
     * @param results Cards returned by the server
     * @return A filtered list of Cards where all cards returned by the server that did not match exactly the criteria were removed.
     */
    public List<Card> filter(List<Card> results) {
        // Combines all filters in one.
        Predicate<Card> allFilters = resultFilters.stream().reduce(Predicate::and).orElse(x -> true);
        return results.stream().filter(allFilters).collect(Collectors.toList());
    }

}
