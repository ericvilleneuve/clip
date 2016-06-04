package com.evil.clip.view;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class LandingViewTest {

    private Document document;

    @Before
    public void thisView() throws Exception {
        LandingView view = new LandingView();
        document = Jsoup.parse(view.render());
    }

    @Test
    public void hasClipTitleAndHeading() throws Exception {
        assertThat(document.title(), is("Cl.ip"));
        assertThat(document.body().select("h2").text(), is("Cl.ip"));
    }

    @Test
    public void includesMainCss() throws Exception {
        assertThat(document.head().select("link[rel=stylesheet][href=/css/main.css]"), notNullValue());
    }
}