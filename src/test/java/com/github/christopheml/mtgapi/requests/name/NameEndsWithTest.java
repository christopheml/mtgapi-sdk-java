package com.github.christopheml.mtgapi.requests.name;

import com.github.christopheml.mtgapi.data.Cards;
import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.requests.CardQuery;
import org.junit.Test;

import java.util.List;

import static com.github.christopheml.mtgapi.requests.CardQuery.query;
import static com.github.christopheml.mtgapi.requests.name.NameMatchers.endsWith;
import static org.assertj.core.api.Assertions.assertThat;

public class NameEndsWithTest {

    @Test
    public void url_parameter_should_be_set() throws Exception {
        String url = query().name(endsWith("Fury")).toUrl("https://api.magicthegathering.io/v1/cards");
        assertThat(url).isEqualTo("https://api.magicthegathering.io/v1/cards?name=Fury");
    }

    @Test
    public void test_result_filters() throws Exception {
        CardQuery cardQuery = query().name(endsWith("Fury"));
        List<Card> cards = Cards.forNames("Chandra's Fury", "Fury Sliver");
        List<Card> filteredCards = cardQuery.filter(cards);
        assertThat(filteredCards).extracting(Card::getName).containsOnly("Chandra's Fury");
    }

}
