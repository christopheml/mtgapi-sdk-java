package com.github.christopheml.mtgapi;

import com.github.christopheml.mtgapi.assertions.CardAssert;
import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.http.JsonHttpClient;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

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

        CardAssert.assertThat(card)
                .hasName("Narset, Enlightened Master")
                .hasManaCost("{3}{U}{R}{W}")
                .hasCmc(6)
                .hasExactType("Legendary Creature — Human Monk")
                .hasText("First strike, hexproof\nWhenever Narset, Enlightened Master attacks, exile the top four cards of your library. Until end of turn, you may cast noncreature cards exiled with Narset this turn without paying their mana costs.")
                .hasArtist("Magali Villeneuve")
                .hasMultiverseId(386616)
                .hasNumber("190")
                .hasImageUrl("http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=386616&type=card")
                .hasWatermark("Jeskai")
                .hasOriginalText("First strike, hexproof\nWhenever Narset, Enlightened Master attacks, exile the top four cards of your library. Until end of turn, you may cast noncreature cards exiled with Narset this turn without paying their mana costs.")
                .hasOriginalType("Legendary Creature — Human Monk")
                .hasId("2a9bdc9658a08072fa90c602c045eb6a0d94e083");
    }

}
