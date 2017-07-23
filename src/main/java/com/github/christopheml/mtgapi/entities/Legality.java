package com.github.christopheml.mtgapi.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.Objects;

public enum Legality {

    LEGAL("Legal"),

    RESTRICTED("Restricted"),

    BANNED("Banned");

    private final String canonicalName;

    Legality(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    @JsonCreator
    public static Legality fromString(String representation) {
        return Arrays.stream(values())
                .filter(legality -> Objects.equals(legality.canonicalName, representation))
                .findFirst()
                .orElseThrow(() -> new UnknownEntityException(Legality.class, representation));
    }

    @Override
    public String toString() {
        return canonicalName;
    }

}
