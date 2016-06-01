package com.evil.clip.http;

import com.evil.clip.Clip;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.vtence.molecule.testing.BodyContent.asText;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CanShowHelloWorld {

    private Server server;
    private Request request = new Request();
    private Response response = new Response();

    private LandingController controller;

    @Before
    public void startServer() throws Exception {
        server = Clip.launch("0.0.0.0", 1818);
        controller = new LandingController();
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }

    @Test
    public void returnsOkWithHelloWorld() throws Exception {
        controller.handle(request, response);

        assertThat(response.statusCode(), is(200));
        assertThat(asText(response), is("Hello World!"));
    }
}
