package com.github.christopheml.mtgapi;

import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.http.JsonHttpClient;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class CardApiTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(8401));

    private static final CardApi cardApi = new CardApi("http://localhost:8401/v1/cards", JsonHttpClient.defaultInstance());

    @Test
    public void fetch_one_card() throws Exception {
        stubFor(get(urlPathEqualTo("/v1/cards/386616")).willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBodyFile("386616.json")
        ));

        Card card = cardApi.singleCard(386616);

        assertThat(card).isNotNull();
    }

}
