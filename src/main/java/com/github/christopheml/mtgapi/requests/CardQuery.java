package com.github.christopheml.mtgapi.requests;

import java.util.HashMap;
import java.util.Map;

public class CardQuery {

    private final Map<String, String> parameters = new HashMap<>();

    public CardQuery withName(String name) {
        parameters.put("name", name);
        return this;
    }

    public static CardQuery query() {
        return new CardQuery();
    }

    public String toUrl(String endpoint) {
        String encodedParameters = parameters.entrySet().stream()
                .map(p -> p.getKey() + "=" + p.getValue())
                .reduce((p1, p2) -> p1 + "&" + p2)
                .map(s -> "?" + s)
                .orElse("");
        return endpoint + encodedParameters;
    }

}
