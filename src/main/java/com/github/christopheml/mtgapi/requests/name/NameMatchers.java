package com.github.christopheml.mtgapi.requests.name;

public final class NameMatchers {

    private NameMatchers() {
    }

    public static NameMatcher equalsTo(String name) {
        return new NameEqualsToMatcher(name);
    }

    public static NameMatcher startsWith(String prefix) {
        return new NameStartsWithMatcher(prefix);
    }

}
