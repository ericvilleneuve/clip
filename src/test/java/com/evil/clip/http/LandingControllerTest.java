package com.evil.clip.http;

import com.evil.clip.view.LandingView;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import org.junit.Before;
import org.junit.Test;

import static com.vtence.molecule.testing.BodyContent.asText;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LandingControllerTest {

    private LandingController controller;
    private LandingView view;
    private Response response = new Response();
    private Request request = new Request();

    @Before
    public void thisController() throws Exception {
        view = mock(LandingView.class);
        controller = new LandingController(view);
    }

    @Test
    public void returnsOk() throws Exception {
        controller.handle(request, response);
        assertThat(response.statusCode(), is(200));
    }

    @Test
    public void outputsViewRender() throws Exception {
        when(view.render()).thenReturn("view-body");
        controller.handle(request, response);

        assertThat(asText(response), is("view-body"));
    }
}