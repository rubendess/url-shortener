package com.rubendess.urlshortener.dto;

import java.io.Serializable;

public class URLDto implements Serializable {

    private Long id;
    private String hashUrl;
    private String url;

    public URLDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashUrl() {
        return hashUrl;
    }

    public void setHashUrl(String hashUrl) {
        this.hashUrl = hashUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
