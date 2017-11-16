package com.github.christopheml.mtgapi.requests;

/**
 * Represents a parameter for the REST API.
 */
class CardQueryParameter {

    private final String name;

    private final String value;

    CardQueryParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String format(String formatString) {
        return String.format(formatString, name, value);
    }

}
