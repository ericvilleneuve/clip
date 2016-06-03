package com.evil.clip.view;

import static com.evil.clip.support.MustacheRenderer.renderTemplate;

public class LandingView {

    public String render() {
        return renderTemplate("views/landing.mustache", this);
    }
}
