package com.github.christopheml.mtgapi.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.Objects;

public enum Rarity {

    COMMON("Common"),

    UNCOMMON("Uncommon"),

    RARE("Rare"),

    MYTHIC_RARE("Mythic Rare"),

    BASIC_LAND("Basic Land"),

    SPECIAL("Special");

    private final String canonicalName;

    Rarity(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    @JsonCreator
    public static Rarity fromString(String representation) {
        return Arrays.stream(values())
                .filter(rarity -> Objects.equals(rarity.canonicalName, representation))
                .findFirst()
                .orElseThrow(() -> new UnknownEntityException(Rarity.class, representation));
    }

    @Override
    public String toString() {
        return canonicalName;
    }

}
