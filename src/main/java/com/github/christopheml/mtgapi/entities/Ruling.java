package com.github.christopheml.mtgapi.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Ruling implements Comparable<Ruling> {

    private final LocalDate date;

    private final String text;

    @JsonCreator
    public Ruling(
            @JsonFormat(pattern = "yyyy-MM-dd") @JsonProperty("date") LocalDate date,
            @JsonProperty("text") String text) {
        this.date = date;
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    @Override
    public int compareTo(Ruling other) {
        return date.compareTo(other.date);
    }

}
