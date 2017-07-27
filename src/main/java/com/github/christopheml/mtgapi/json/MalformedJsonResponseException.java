package com.github.christopheml.mtgapi.json;

import java.io.IOException;

/**
 * Indicates the JSON response from the API had an unexpected format.
 */
public class MalformedJsonResponseException extends RuntimeException {

    MalformedJsonResponseException(IOException e) {
        super(e);
    }

    MalformedJsonResponseException(String message) {
        super(message);
    }

}
