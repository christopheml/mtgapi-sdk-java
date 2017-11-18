package com.github.christopheml.mtgapi.requests.name;

import com.github.christopheml.mtgapi.data.Cards;
import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.requests.CardQuery;
import org.junit.Test;

import java.util.List;

import static com.github.christopheml.mtgapi.requests.CardQuery.query;
import static com.github.christopheml.mtgapi.requests.name.NameMatchers.equalsTo;
import static org.assertj.core.api.Assertions.assertThat;

public class NameEqualsToTest {

    @Test
    public void url_parameter_should_be_set() throws Exception {
        String url = query().name(equalsTo("Darkness")).toUrl("https://api.magicthegathering.io/v1/cards");
        assertThat(url).isEqualTo("https://api.magicthegathering.io/v1/cards?name=Darkness");
    }

    @Test
    public void test_result_filters() throws Exception {
        CardQuery cardQuery = query().name(equalsTo("Darkness"));
        List<Card> cards = Cards.forNames("Infernal Darkness", "Grasp of Darkness", "Clinging Darkness", "Darkness");
        List<Card> filteredCards = cardQuery.filter(cards);
        assertThat(filteredCards).extracting(Card::getName).containsExactly("Darkness");
    }

}
