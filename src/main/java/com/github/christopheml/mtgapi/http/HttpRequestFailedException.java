package com.github.christopheml.mtgapi.http;

import java.io.IOException;

/**
 * Indicates that a HTTP request failed with no response from the server.
 */
public class HttpRequestFailedException extends RuntimeException {

    HttpRequestFailedException(IOException e) {
        super(e);
    }

}
