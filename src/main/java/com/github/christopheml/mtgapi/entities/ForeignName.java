package com.github.christopheml.mtgapi.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ForeignName {

    private final long multiverseid;

    private final String name;

    private final String language;

    private final String imageUrl;

    @JsonCreator
    public ForeignName(
            @JsonProperty("multiverseid") long multiverseid,
            @JsonProperty("name") String name,
            @JsonProperty("language") String language,
            @JsonProperty("imageUrl") String imageUrl) {
        this.multiverseid = multiverseid;
        this.name = name;
        this.language = language;
        this.imageUrl = imageUrl;
    }

    public long getMultiverseid() {
        return multiverseid;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        return multiverseid == ((ForeignName) other).multiverseid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(multiverseid);
    }

}
