package com.evil.clip.http;

import com.evil.clip.domain.UrlRepository;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ShortenControllerTest {

    private ShortenController controller;
    private UrlRepository urlRepository;
    private Response response = new Response();
    private Request request = new Request();

    @Before
    public void thisController() throws Exception {
        urlRepository = mock(UrlRepository.class);
        controller = new ShortenController(urlRepository);
    }

    @Test
    public void returnsOk() throws Exception {
        controller.handle(request, response);
        assertThat(response.statusCode(), is(200));
    }

    @Test
    public void generatesShortenedUrl() throws Exception {
        request.addParameter("url", "original-url");

        controller.handle(request, response);
        verify(urlRepository).add("k9uqh", "original-url");
    }
}