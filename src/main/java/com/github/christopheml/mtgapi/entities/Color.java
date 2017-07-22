package com.github.christopheml.mtgapi.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.Objects;

public enum Color {

    WHITE("White", "W"),

    BLUE("Blue", "U"),

    BLACK("Black", "B"),

    RED("Red", "R"),

    GREEN("Green", "G");

    private final String canonicalName;

    private final String initial;

    Color(String canonicalName, String initial) {
        this.canonicalName = canonicalName;
        this.initial = initial;
    }

    private boolean isRepresentedBy(String representation) {
        return Objects.equals(representation, canonicalName) || Objects.equals(representation, initial);
    }

    @JsonCreator
    public static Color fromString(String representation) {
        return Arrays.stream(values())
                .filter(color -> color.isRepresentedBy(representation))
                .findFirst()
                .orElseThrow(() -> new UnknownEntityException(representation));
    }

    @Override
    public String toString() {
        return canonicalName;
    }

}
