package com.evil.clip.feature;

import com.evil.clip.http.Server;
import com.vtence.molecule.testing.http.HttpRequest;
import com.vtence.molecule.testing.http.HttpResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.evil.clip.Clip.launch;
import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertThat;

public class CanSurviveAppRestart {

    private Server server;
    private String host = "0.0.0.0";
    private int port = 1818;

    private String expectedShortUrl;

    @Before
    public void setUp() throws Exception {
        server = launch(host, port);

        HttpRequest request = new HttpRequest(host, port);
        HttpResponse response = request.get("/shorten?url=http://original-loooong.url");
        expectedShortUrl = response.bodyText();
    }

    @Test
    public void getsSameExpandedUrlAfterRestart() throws Exception {
        HttpRequest request = new HttpRequest(host, port);
        HttpResponse response = request.get("/" + expectedShortUrl);

        assertThat(response.bodyText(), endsWith("http://original-loooong.url"));

        server.stop();
        server.start();

        response = request.get("/" + expectedShortUrl);
        assertThat(response.bodyText(), endsWith("http://original-loooong.url"));
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

}
