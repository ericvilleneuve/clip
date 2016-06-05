package com.evil.clip.view;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LandingViewTest {

    private Document document;

    @Before
    public void thisView() throws Exception {
        LandingView view = new LandingView();
        document = Jsoup.parse(view.render());
    }

    @Test
    public void hasClipTitleAndHeading() {
        assertThat(document.title(), is("Cl.ip"));
        assertThat(document.body().select("h2").text(), is("Cl.ip"));
    }

    @Test
    public void includesMainCss() {
        assertThat(document.select("link[rel=stylesheet][href=/css/main.css]").first(), notNullValue());
    }

    @Test
    public void includesJquery() {
        assertThat(document.head().select("script[src=/scripts/vendor/jquery-2.2.3.min.js]").first(), notNullValue());
    }

    @Test
    public void hasFieldForUrlToShorten() {
        Element urlField = document.body().select("input#url-to-shorten").first();

        assertThat("Url field is missing.", urlField, notNullValue());
        assertThat(urlField.attr("type"), is("url"));
        assertThat(urlField.attr("placeholder"), is("Paste a link to shorten it"));
        assertTrue(urlField.hasAttr("autofocus"));
        assertThat(urlField.attr("autocomplete"), is("off"));
    }

    @Test
    public void hasButtonToShorten() {
        Element button = document.body().select("button").first();

        assertThat("Button is missing.", button, notNullValue());
        assertThat(button.attr("type"), is("button"));
        assertThat(button.text(), is("Shorten"));
    }

    @Test
    public void shortenerScriptIsIncluded() {
        Element shortenerInclude = document.body().select("script[src=/scripts/shortener.js").first();
        assertThat("Shortener script not included.", shortenerInclude, notNullValue());
    }

    @Test
    public void widgetScriptAreIncluded() {
        Element widgetScript = document.getElementById("inline-page-script");
        assertThat(widgetScript.html().trim(), is("new Shortener(\"url-to-shorten\", \"shorten-button\");"));
    }

}