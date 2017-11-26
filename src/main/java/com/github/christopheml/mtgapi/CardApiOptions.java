package com.github.christopheml.mtgapi;

/**
 * Configuration class for the Card API.
 */
public class CardApiOptions {

    private String endpoint;

    /**
     * Creates a default set of options.
     */
    public static CardApiOptions options() {
        return new CardApiOptions();
    }

    /**
     * Specifies a different enpoint for the API.
     *
     * <p>This is useful if you want to test the SDK against a stub.</p>
     */
    public CardApiOptions withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    private CardApiOptions() {
        loadDefaults();
    }

    String getEndpoint() {
        return endpoint;
    }

    private void loadDefaults() {
        endpoint = "https://api.magicthegathering.io/v1/cards";
    }

}
