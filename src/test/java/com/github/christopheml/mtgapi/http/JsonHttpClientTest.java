package com.github.christopheml.mtgapi.http;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.christopheml.mtgapi.responses.ApiResponse;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonHttpClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(8401));

    private static final JsonHttpClient httpClient = JsonHttpClient.defaultInstance();

    @Test
    public void simple_get_to_entity() throws Exception {
        stubFor(get(urlEqualTo("/entities/sample")).willReturn(aResponse()
            .withHeader("Content-Type", "application/json")
            .withBody("{\"name\": \"Angus\", \"count\": 1471, \"flags\": [false, true, true]}")
        ));

        SampleResponse response = (SampleResponse) httpClient.get("http://localhost:8401/entities/sample", SampleResponse.class);
        SampleEntity entity = response.getEntity();
        assertThat(entity.getName()).isEqualTo("Angus");
        assertThat(entity.getCount()).isEqualTo(1471);
        assertThat(entity.getFlags()).containsExactly(false, true, true);
    }

    private static final class SampleResponse extends ApiResponse {

        private final SampleEntity entity;

        @JsonCreator
        public SampleResponse(SampleEntity entity) {
            this.entity = entity;
        }

        public SampleEntity getEntity() {
            return entity;
        }
    }

    private static final class SampleEntity {

        private String name;

        private Integer count;

        private List<Boolean> flags;

        String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List<Boolean> getFlags() {
            return flags;
        }

        public void setFlags(List<Boolean> flags) {
            this.flags = flags;
        }

    }

}
