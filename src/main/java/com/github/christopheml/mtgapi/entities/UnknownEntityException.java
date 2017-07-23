package com.github.christopheml.mtgapi.entities;

/**
 * Indicates that the data returned by the API contained an entity unknown to this SDK version.
 */
public class UnknownEntityException extends RuntimeException {

    public UnknownEntityException(Class<?> expectedClass, String entity) {
        super(String.format("The entity <%s> is unknown for type <%s>, your mtgapi-sdk-java version may be out of date", entity, expectedClass.getSimpleName()));
    }

}
