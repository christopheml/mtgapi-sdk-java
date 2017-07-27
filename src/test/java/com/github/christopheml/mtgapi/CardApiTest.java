package com.github.christopheml.mtgapi;

import com.github.christopheml.mtgapi.assertions.CardAssert;
import com.github.christopheml.mtgapi.entities.Card;
import com.github.christopheml.mtgapi.entities.Color;
import com.github.christopheml.mtgapi.entities.Layout;
import com.github.christopheml.mtgapi.entities.Rarity;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class CardApiTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(8401));

    private static final CardApi cardApi = new CardApi("http://localhost:8401/v1/cards", Application.httpClient());

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
                .hasExactColors(Color.BLUE, Color.RED, Color.WHITE)
                .hasColorIdentity(Color.BLUE, Color.RED, Color.WHITE)
                .hasExactType("Legendary Creature — Human Monk")
                .hasExactSupertypes("Legendary")
                .hasExactTypes("Creature")
                .hasExactSubtypes("Human", "Monk")
                .hasRarity(Rarity.MYTHIC_RARE)
                .hasPower(3)
                .hasThougness(2)
                .hasText("First strike, hexproof\nWhenever Narset, Enlightened Master attacks, exile the top four cards of your library. Until end of turn, you may cast noncreature cards exiled with Narset this turn without paying their mana costs.")
                .hasArtist("Magali Villeneuve")
                .hasMultiverseId(386616)
                .hasSet("KTK")
                .hasSetName("Khans of Tarkir")
                .hasNumber("190")
                .hasLayout(Layout.NORMAL)
                .hasImageUrl("http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=386616&type=card")
                .hasWatermark("Jeskai")
                .hasOriginalText("First strike, hexproof\nWhenever Narset, Enlightened Master attacks, exile the top four cards of your library. Until end of turn, you may cast noncreature cards exiled with Narset this turn without paying their mana costs.")
                .hasOriginalType("Legendary Creature — Human Monk")
                .hasId("2a9bdc9658a08072fa90c602c045eb6a0d94e083")
                .hasExactlyForeignNamesFor("Chinese Simplified", "Chinese Traditional", "French",  "German", "Italian", "Japanese", "Korean", "Portuguese (Brazil)", "Russian", "Spanish");

        assertThat(card.getRulings()).hasSize(5);
    }

}
