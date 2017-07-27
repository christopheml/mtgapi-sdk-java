package com.github.christopheml.mtgapi.http;

import com.github.christopheml.mtgapi.Application;
import com.github.christopheml.mtgapi.responses.SingleResponse;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class HttpClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(8401));

    private static final HttpClient httpClient = Application.httpClient();

    @Test
    public void simple_get_to_entity() throws Exception {
        stubFor(get(urlEqualTo("/entities/sample")).willReturn(aResponse()
            .withHeader("Content-Type", "application/json")
            .withBody("{\"sample\": {\"name\": \"Angus\", \"count\": 1471, \"flags\": [false, true, true]} }")
        ));

        SampleResponse response = httpClient.get("http://localhost:8401/entities/sample", SampleResponse::new);
        SampleEntity entity = response.getEntity();
        assertThat(entity.getName()).isEqualTo("Angus");
        assertThat(entity.getCount()).isEqualTo(1471);
        assertThat(entity.getFlags()).containsExactly(false, true, true);
    }

    private static final class SampleResponse extends SingleResponse<SampleEntity> {

        @Override
        public Class<SampleEntity> getEntityClass() {
            return SampleEntity.class;
        }

        @Override
        public String getEntityRoot() {
            return "sample";
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
