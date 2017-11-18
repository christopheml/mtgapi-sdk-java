package com.github.christopheml.mtgapi.requests;

import com.github.christopheml.mtgapi.data.Cards;
import com.github.christopheml.mtgapi.entities.Card;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardQueryTest {

    @Test
    public void empty_query_should_not_filter_out_any_card() throws Exception {
        CardQuery query = CardQuery.query();
        List<Card> cards = Cards.forNames("Plains", "Ondu Cleric");

        assertThat(query.filter(cards)).containsExactlyElementsOf(cards);
    }

    @Test
    public void empty_query_should_not_alter_url() throws Exception {
        String endpoint = "https://api.magicthegathering.io/v1/cards";
        CardQuery query = CardQuery.query();

        assertThat(query.toUrl(endpoint)).isEqualTo(endpoint);
    }

}
