package com.github.christopheml.mtgapi.requests;

public abstract class NameMatcher implements CardMatcher {

    public static NameMatcher equalsTo(String name) {
        return new NameEqualsToMatcher(name);
    }

    public static NameMatcher startsWith(String prefix) {
        return new NameStartsWithMatcher(prefix);
    }

}
