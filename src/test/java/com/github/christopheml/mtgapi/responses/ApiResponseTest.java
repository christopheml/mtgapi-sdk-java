package com.github.christopheml.mtgapi.responses;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.message.BasicHttpResponse;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiResponseTest {

    @Test
    public void should_read_page_size_header()  {
        HttpResponse httpResponse = responseWithHeader("Page-Size", "100");
        ApiResponse<Object> apiResponse = new SampleResponse();

        apiResponse.setPageSize(httpResponse);

        assertThat(apiResponse.getPageSize()).isEqualTo(100);
    }

    @Test
    public void should_read_total_count_header()  {
        HttpResponse httpResponse = responseWithHeader("Total-Count", "2877");
        ApiResponse<Object> apiResponse = new SampleResponse();

        apiResponse.setTotalCount(httpResponse);

        assertThat(apiResponse.getTotalCount()).isEqualTo(2877);
    }

    @Test
    public void should_read_count_header() {
        HttpResponse httpResponse = responseWithHeader("Count", "98");
        ApiResponse<Object> apiResponse = new SampleResponse();

        apiResponse.setCount(httpResponse);

        assertThat(apiResponse.getCount()).isEqualTo(98);
    }

    @Test
    public void should_read_ratelimit_remaining_header()  {
        HttpResponse httpResponse = responseWithHeader("Ratelimit-Remaining", "4788");
        ApiResponse<Object> apiResponse = new SampleResponse();

        apiResponse.setRatelimitRemaining(httpResponse);

        assertThat(apiResponse.getRatelimitRemaining()).isEqualTo(4788);
    }

    @Test
    public void should_read_links_header() throws Exception {
        HttpResponse httpResponse = responseWithHeader("Link",
                "<http://api.magicthegathering.io/v1/cards?page=311>; rel=\"last\", <http://api.magicthegathering.io/v1/cards?page=2>; rel=\"next\"");
        ApiResponse<Object> apiResponse = new SampleResponse();

        apiResponse.setLinks(httpResponse);

        assertThat(apiResponse.getLink("last")).contains("http://api.magicthegathering.io/v1/cards?page=311");
        assertThat(apiResponse.getLink("next")).contains("http://api.magicthegathering.io/v1/cards?page=2");
        assertThat(apiResponse.getLink("prev")).isEmpty();
    }

    private HttpResponse responseWithHeader(String headerName, String headerValue) {
        HttpResponse response = new BasicHttpResponse(new ProtocolVersion("HTTP", 1, 1), 200, "OK");
        response.setHeader(headerName, headerValue);
        return response;
    }

    private static final class SampleResponse extends SingleResponse<Object> {
        @Override
        public Class<Object> getEntityClass() {
            return Object.class;
        }

        @Override
        public String getEntityRoot() {
            return "";
        }
    }

}
