package com.evil.clip.http;

import com.evil.clip.domain.UrlRepository;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import org.junit.Before;
import org.junit.Test;

import static com.vtence.molecule.testing.BodyContent.asText;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ExpandControllerTest {

    ExpandController controller;
    private Response response = new Response();
    private Request request = new Request();
    private UrlRepository urlRepository;

    @Before
    public void thisController() throws Exception {
        urlRepository = mock(UrlRepository.class);
        controller = new ExpandController(urlRepository);
    }

    @Test
    public void returnsOk() throws Exception {
        controller.handle(request, response);
        assertThat(response.statusCode(), is(200));
    }

    @Test
    public void retrievesOriginalUrlFromShortened() throws Exception {
        request.addParameter("hashed-url", "jkpokypokp");
        controller.handle(request, response);

        verify(urlRepository).findByShortUrl("jkpokypokp");
    }

    @Test
    public void returnsOriginalUrlWhenItExists() throws Exception {
        request.addParameter("hashed-url", "fjknfkjnf");

        when(urlRepository.findByShortUrl("fjknfkjnf")).thenReturn("http://original-long.url");
        controller.handle(request, response);

        assertThat(asText(response), is("http://original-long.url"));
    }
}
