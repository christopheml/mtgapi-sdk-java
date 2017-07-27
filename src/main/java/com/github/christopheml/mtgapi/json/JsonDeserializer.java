package com.github.christopheml.mtgapi.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.christopheml.mtgapi.responses.ListResponse;
import com.github.christopheml.mtgapi.responses.SingleResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class JsonDeserializer {

    private final ObjectMapper objectMapper;

    public JsonDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <ENTITY, T extends SingleResponse<ENTITY>> ENTITY deserialize(InputStream json, T singleResponse) {
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode entityRoot = Optional
                    .ofNullable(root.findValue(singleResponse.getEntityRoot()))
                    .orElseThrow(() -> new MalformedJsonResponseException(String.format("Can't find root element %s in response", singleResponse.getEntityRoot())));
            return objectMapper.treeToValue(entityRoot, singleResponse.getEntityClass());
        } catch (IOException e) {
            throw new MalformedJsonResponseException(e);
        }
    }

    public <ENTITY, T extends ListResponse<ENTITY>> List<ENTITY> deserialize(InputStream json, T listResponse) {
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode entityRoot = Optional
                    .ofNullable(root.findValue(listResponse.getEntityRoot()))
                    .orElseThrow(() -> new MalformedJsonResponseException(String.format("Can't find root element %s in response", listResponse.getEntityRoot())));
           return objectMapper.readValue(entityRoot.traverse(), new TypeReference<List<ENTITY>>(){});
        } catch (IOException e) {
            throw new MalformedJsonResponseException(e);
        }
    }

}
