package com.github.christopheml.mtgapi;

public class CardApiOptions {

    private String endpoint;

    public static CardApiOptions options() {
        return new CardApiOptions();
    }

    public CardApiOptions withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    private CardApiOptions() {
        loadDefaults();
    }

    public String getEndpoint() {
        return endpoint;
    }

    private void loadDefaults() {
        endpoint = "https://api.magicthegathering.io/v1/cards";
    }

}
