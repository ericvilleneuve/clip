package com.evil.clip.view;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LandingViewTest {

    private Document document;

    @Before
    public void thisView() throws Exception {
        LandingView view = new LandingView();
        document = Jsoup.parse(view.render());
    }

    @Test
    public void saysHelloWorld() throws Exception {
        assertThat(document.body().text(), is("Hello World!"));
    }
}