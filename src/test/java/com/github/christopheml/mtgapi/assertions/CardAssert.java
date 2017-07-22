package com.github.christopheml.mtgapi.assertions;

import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.entities.Color;
import com.github.christopheml.mtgapi.entities.Rarity;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Iterables;

import java.util.Objects;

public final class CardAssert extends AbstractAssert<CardAssert, Card> {

    private static final Iterables iterables = Iterables.instance();

    private CardAssert(Card actual) {
        super(actual, CardAssert.class);
    }

    public static CardAssert assertThat(Card actual) {
        return new CardAssert(actual);
    }

    public CardAssert hasName(String name) {
        isNotNull();

        if (!Objects.equals(actual.getName(), name)) {
            failWithMessage("Expected card's name to be <%s> but was <%s>", name, actual.getName());
        }

        return this;
    }

    public CardAssert hasManaCost(String manaCost) {
        isNotNull();

        if (!Objects.equals(actual.getManaCost(), manaCost)) {
            failWithMessage("Expected card's mana cost to be <%s> but was <%s>", manaCost, actual.getManaCost());
        }

        return this;
    }

    public CardAssert hasCmc(int cmc) {
        isNotNull();

        if (actual.getCmc() != cmc) {
            failWithMessage("Expected card's converted mana cost to be <%s> but was <%s>", cmc, actual.getCmc());
        }

        return this;
    }

    public CardAssert hasExactType(String type) {
        isNotNull();

        if (!Objects.equals(actual.getType(), type)) {
            failWithMessage("Expected card's type to be <%s> but was <%s>", type, actual.getType());
        }

        return this;
    }

    public CardAssert hasText(String text) {
        isNotNull();

        if (!Objects.equals(actual.getText(), text)) {
            failWithMessage("Expected card's text to be <%s> but was <%s>", text, actual.getText());
        }

        return this;
    }

    public CardAssert hasArtist(String artist) {
        isNotNull();

        if (!Objects.equals(actual.getArtist(), artist)) {
            failWithMessage("Expected card's artist to be <%s> but was <%s>", artist, actual.getArtist());
        }

        return this;
    }

    public CardAssert hasMultiverseId(long multiverseId) {
        isNotNull();

        if (actual.getMultiverseid() != multiverseId) {
            failWithMessage("Expected card's multiverse id to be <%s> but was <%s>", multiverseId, actual.getMultiverseid());
        }

        return this;
    }

    public CardAssert hasNumber(String number) {
        isNotNull();

        if (!Objects.equals(actual.getNumber(), number)) {
            failWithMessage("Expected card's number to be <%s> but was <%s>", number, actual.getNumber());
        }

        return this;
    }

    public CardAssert hasImageUrl(String imageUrl) {
        isNotNull();

        if (!Objects.equals(actual.getImageUrl(), imageUrl)) {
            failWithMessage("Expected card's image url to be <%s> but was <%s>", imageUrl, actual.getImageUrl());
        }

        return this;
    }

    public CardAssert hasWatermark(String watermark) {
        isNotNull();

        if (!Objects.equals(actual.getWatermark(), watermark)) {
            failWithMessage("Expected card's watermark to be <%s> but was <%s>", watermark, actual.getWatermark());
        }

        return this;
    }

    public CardAssert hasOriginalText(String originalText) {
        isNotNull();

        if (!Objects.equals(actual.getOriginalText(), originalText)) {
            failWithMessage("Expected card's original text to be <%s> but was <%s>", originalText, actual.getOriginalText());
        }

        return this;
    }

    public CardAssert hasOriginalType(String originalType) {
        isNotNull();

        if (!Objects.equals(actual.getOriginalType(), originalType)) {
            failWithMessage("Expected card's original type to be <%s> but was <%s>", originalType, actual.getOriginalType());
        }

        return this;
    }

    public CardAssert hasId(String id) {
        isNotNull();

        if (!Objects.equals(actual.getId(), id)) {
            failWithMessage("Expected card's id to be <%s> but was <%s>", id, actual.getId());
        }

        return this;
    }

    public CardAssert hasColorIdentity(Color... colors) {
        isNotNull();

        iterables.assertContainsExactlyInAnyOrder(info, actual.getColorIdentity(), colors);

        return this;
    }

    public CardAssert hasExactColors(Color... colors) {
        isNotNull();

        iterables.assertContainsExactlyInAnyOrder(info, actual.getColors(), colors);

        return this;
    }

    public CardAssert hasRarity(Rarity rarity) {
        isNotNull();

        if (actual.getRarity() != rarity) {
            failWithMessage("Expected card's rarity id to be <%s> but was <%s>", rarity, actual.getRarity());
        }

        return this;
    }

}
