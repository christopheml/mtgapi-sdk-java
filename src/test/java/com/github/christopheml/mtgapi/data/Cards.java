package com.github.christopheml.mtgapi.data;

import com.github.christopheml.mtgapi.entities.Card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creates simple test data.
 */
public class Cards {

    /**
     * Creates a new card with a given name.
     */
    public static Card forName(String name) {
        Card card = new Card();
        card.setName(name);
        return card;
    }

    /**
     * Creates new cards with the given names.
     */
    public static List<Card> forNames(String... names) {
        return Arrays.stream(names).map(Cards::forName).collect(Collectors.toList());
    }

}
