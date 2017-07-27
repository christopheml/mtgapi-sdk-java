package com.github.christopheml.mtgapi.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.christopheml.mtgapi.responses.SingleResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.function.Supplier;

public class JsonDeserializer {

    private final ObjectMapper objectMapper;

    public JsonDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <ENTITY, T extends SingleResponse<ENTITY>> T deserialize(InputStream json, Supplier<T> responseSupplier) {
        T singleResponse = responseSupplier.get();

        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode entityRoot = Optional
                    .ofNullable(root.findValue(singleResponse.getEntityRoot()))
                    .orElseThrow(() -> new MalformedJsonResponseException(String.format("Can't find root element %s in response", singleResponse.getEntityRoot())));
            ENTITY entity = objectMapper.treeToValue(entityRoot, singleResponse.getEntityClass());
            singleResponse.setEntity(entity);
            return singleResponse;
        } catch (IOException e) {
            throw new MalformedJsonResponseException(e);
        }
    }


}
