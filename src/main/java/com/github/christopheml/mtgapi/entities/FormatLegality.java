package com.github.christopheml.mtgapi.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class FormatLegality {

    private final String format;

    private final Legality legality;

    @JsonCreator
    public FormatLegality(
            @JsonProperty("format") String format,
            @JsonProperty("legality") Legality legality) {
        this.format = format;
        this.legality = legality;
    }

    public String getFormat() {
        return format;
    }

    public Legality getLegality() {
        return legality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormatLegality that = (FormatLegality) o;
        return Objects.equals(format, that.format);
    }

    @Override
    public int hashCode() {
        return Objects.hash(format);
    }

}
