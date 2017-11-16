package com.github.christopheml.mtgapi.requests;

import java.util.Map;

public abstract class NameMatcher implements CardMatcher {

    @Override
    public void appendParameter(Map<String, String> parameters) {
        parameterValue().ifPresent(value -> parameters.put("name", value));
    }

    public static NameMatcher equalsTo(String name) {
        return new NameEqualsToMatcher(name);
    }

    public static NameMatcher startsWith(String prefix) {
        return new NameStartsWithMatcher(prefix);
    }

}
