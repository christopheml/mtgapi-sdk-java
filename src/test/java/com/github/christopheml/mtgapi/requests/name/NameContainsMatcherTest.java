package com.github.christopheml.mtgapi.requests.name;

import com.github.christopheml.mtgapi.data.Cards;
import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.requests.CardQuery;
import org.junit.Test;

import java.util.List;

import static com.github.christopheml.mtgapi.requests.CardQuery.query;
import static com.github.christopheml.mtgapi.requests.name.NameMatchers.contains;
import static org.assertj.core.api.Assertions.assertThat;

public class NameContainsMatcherTest {

    @Test
    public void url_parameter_should_be_set() throws Exception {
        String url = query().name(contains("Darkness")).toUrl("https://api.magicthegathering.io/v1/cards");
        assertThat(url).isEqualTo("https://api.magicthegathering.io/v1/cards?name=Darkness");
    }

    @Test
    public void test_result_filters() throws Exception {
        CardQuery cardQuery = query().name(contains("Dark"));
        List<Card> cards = Cards.forNames("Dark Ritual", "Grasp of Darkness", "Darkness");
        List<Card> filteredCards = cardQuery.filter(cards);
        assertThat(filteredCards).containsExactlyElementsOf(cards);
    }

}
