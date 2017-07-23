package com.github.christopheml.mtgapi.requests;

import org.junit.Test;

import static com.github.christopheml.mtgapi.requests.CardQuery.query;
import static org.assertj.core.api.Assertions.assertThat;

public class CardQueryTest {

    @Test
    public void test_name_parameter() throws Exception {
        String url = query().withName("Darkness").toUrl("https://api.magicthegathering.io/v1/cards");
        assertThat(url).isEqualTo("https://api.magicthegathering.io/v1/cards?name=Darkness");
    }

}
