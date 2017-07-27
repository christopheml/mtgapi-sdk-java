package com.github.christopheml.mtgapi.responses;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ApiResponse<ENTITY> {

    private static final Pattern LINK_PATTERN = Pattern.compile("<([^>]+)>; rel=\"([^\"]+)\"");

    private Integer pageSize;

    private Integer count;

    private Integer totalCount;

    private Integer ratelimitRemaining;

    private final Map<String, String> links = new HashMap<>();

    public void setLinks(HttpResponse response) {
        Header linksHeader = response.getFirstHeader("Link");
        if (linksHeader != null) {
            String rawLinks = linksHeader.getValue();
            Matcher matcher = LINK_PATTERN.matcher(rawLinks);
            while (matcher.find()) {
                links.put(matcher.group(2), matcher.group(1));
            }
        }
    }

    public void setCount(HttpResponse response) {
        Header countHeader = response.getFirstHeader("Count");
        if (countHeader != null) {
            count = Integer.parseInt(countHeader.getValue());
        }
    }

    public void setPageSize(HttpResponse response) {
        Header pageSizeHeader = response.getFirstHeader("Page-Size");
        if (pageSizeHeader != null) {
            pageSize = Integer.parseInt(pageSizeHeader.getValue());
        }
    }

    public void setTotalCount(HttpResponse response) {
        Header totalCountHeader = response.getFirstHeader("Total-Count");
        if (totalCountHeader != null) {
            totalCount = Integer.parseInt(totalCountHeader.getValue());
        }
    }

    public void setRatelimitRemaining(HttpResponse response) {
        Header ratelimitRemainingHeader = response.getFirstHeader("Ratelimit-Remaining");
        if (ratelimitRemainingHeader != null) {
            ratelimitRemaining = Integer.parseInt(ratelimitRemainingHeader.getValue());
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Integer getRatelimitRemaining() {
        return ratelimitRemaining;
    }

    public Optional<String> getLink(String rel) {
        return Optional.ofNullable(links.get(rel));
    }

    public abstract Class<ENTITY> getEntityClass();

    public abstract String getEntityRoot();

}
