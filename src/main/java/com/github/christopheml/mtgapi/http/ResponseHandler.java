package com.github.christopheml.mtgapi.http;

import org.apache.http.HttpResponse;

import java.io.IOException;

/**
 * Processes a single HTTP response.
 *
 * <p>This class was introduced separate response handling from the request execution itself.</p>
 */
@FunctionalInterface
interface ResponseHandler {

    void apply(HttpResponse httpResponse) throws IOException;

}
