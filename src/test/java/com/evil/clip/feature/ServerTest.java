package com.evil.clip.feature;

import com.evil.clip.Clip;
import com.evil.clip.http.Server;
import com.vtence.molecule.testing.http.HttpRequest;
import com.vtence.molecule.testing.http.HttpResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ServerTest {

    private Server server;
    private HttpRequest request;

    @Before
    public void thisServer() throws Exception {
        server = Clip.launch("0.0.0.0", 1818);
        request = new HttpRequest("0.0.0.0", 1818);
    }

    @Test
    public void respondsOnSlash() throws IOException {
        HttpResponse response = request.get("/");
        assertThat(response.statusCode(), is(200));
    }

    @Test
    public void respondsOnSlashShorten() throws IOException {
        HttpResponse response = request.get("/shorten");
        assertThat(response.statusCode(), is(200));
    }

    @Test
    public void respondsOnDynamicRouteWithHashedUrl() throws IOException {
        HttpResponse response = request.get("/shorten?url=http://some-looooong.url");
        String hashedUrl = response.bodyText();
        response = request.get("/" + hashedUrl);

        assertThat(response.statusCode(), is(200));
        assertThat(response.bodyText(), is("http://some-looooong.url"));
    }

    @Test
    public void canServeCssFiles() throws Exception {
        HttpResponse response = request.get("/css/main.css");
        assertThat(response.statusCode(), is(200));
    }

    @Test
    public void canServeJsFiles() throws Exception {
        HttpResponse response = request.get("/scripts/shortener.js");
        assertThat(response.statusCode(), is(200));
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }
}
