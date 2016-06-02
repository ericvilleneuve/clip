package com.evil.clip.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UrlRepositoryTest {

    UrlRepository repository;

    @Before
    public void thisRepository() throws Exception {
        repository = new UrlRepository();
        repository.add("some-short-url", "http://some-original.url");
    }

    @Test
    public void findsOriginalUrlByShortUrl() throws Exception {
        String originalUrl = repository.findByShortUrl("some-short-url");

        assertThat(originalUrl, is("http://some-original.url"));
    }
}