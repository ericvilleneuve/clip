package com.evil.clip.view;

import static com.evil.clip.support.MustacheRenderer.renderTemplate;

public class ShortenFormView {

    public String render() {
        return renderTemplate("views/shorten-form.mustache", this);
    }
}
