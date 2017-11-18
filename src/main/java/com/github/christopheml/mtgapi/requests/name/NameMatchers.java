package com.github.christopheml.mtgapi.requests.name;

public final class NameMatchers {

    private NameMatchers() {
    }

    public static NameMatcher equalsTo(String name) {
        return new NameEqualsTo(name);
    }

    public static NameMatcher contains(String fragment) {
        return new NameContains(fragment);
    }

    public static NameMatcher startsWith(String prefix) {
        return new NameStartsWith(prefix);
    }

    public static NameMatcher endsWith(String suffix) {
        return new NameEndsWith(suffix);
    }

}
