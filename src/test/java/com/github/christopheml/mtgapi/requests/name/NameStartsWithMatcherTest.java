package com.github.christopheml.mtgapi.requests.name;

import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.requests.CardQuery;
import org.junit.Test;

import java.util.List;

import static com.github.christopheml.mtgapi.requests.CardQuery.query;
import static com.github.christopheml.mtgapi.requests.name.NameMatchers.equalsTo;
import static com.github.christopheml.mtgapi.requests.name.NameMatchers.startsWith;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class NameStartsWithMatcherTest {

    @Test
    public void url_parameter_should_be_set() throws Exception {
        String url = query().name(equalsTo("Darkness")).toUrl("https://api.magicthegathering.io/v1/cards");
        assertThat(url).isEqualTo("https://api.magicthegathering.io/v1/cards?name=Darkness");
    }

    @Test
    public void test_result_filters() throws Exception {
        CardQuery cardQuery = query().name(startsWith("Darkness"));
        List<Card> cards = asList(cardWithName("Infernal Darkness"), cardWithName("Grasp of Darkness"), cardWithName("Clinging Darkness"), cardWithName("Darkness"));
        List<Card> filteredCards = cardQuery.filter(cards);
        assertThat(filteredCards).extracting(Card::getName).containsOnly("Darkness");
    }

    private Card cardWithName(String name) {
        Card card = new Card();
        card.setName(name);
        return card;
    }

}
