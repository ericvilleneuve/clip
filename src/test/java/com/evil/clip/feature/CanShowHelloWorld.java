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

public class CanShowHelloWorld {

    private Server server;
    private HttpRequest request;

    @Before
    public void startServer() throws Exception {
        server = Clip.launch("0.0.0.0", 1818);
        request = new HttpRequest("0.0.0.0", 1818);
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
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
    public void respondsOnSlashExpand() throws IOException {
        HttpResponse response = request.get("/expand");
        assertThat(response.statusCode(), is(200));
    }

}
