package com.github.christopheml.mtgapi.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.Objects;

public enum Layout {

    AFTERMATH("aftermath"),

    DOUBLE_FACED("double-faced"),

    FLIP("flip"),

    LEVELER("leveler"),

    MELD("meld"),

    NORMAL("normal"),

    PHENOMENON("phenomenon"),

    PLANE("plane"),

    SCHEME("scheme"),

    SPLIT("split"),

    TOKEN("token"),

    VANGUARD("vanguard");

    private final String canonicalName;

    Layout(String canonicalName) {

        this.canonicalName = canonicalName;
    }

    @JsonCreator
    public static Layout fromString(String representation) {
        return Arrays.stream(values())
                .filter(layout -> Objects.equals(layout.canonicalName, representation))
                .findFirst()
                .orElseThrow(() -> new UnknownEntityException(Layout.class, representation));
    }

    @Override
    public String toString() {
        return canonicalName;
    }

}
