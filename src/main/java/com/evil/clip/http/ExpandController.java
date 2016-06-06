package com.evil.clip.http;

import com.evil.clip.domain.UrlRepository;
import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.HttpStatus;

public class ExpandController implements Application {

    private UrlRepository urlRepository;

    public ExpandController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public void handle(Request request, Response response) throws Exception {
        String originalUrl = urlRepository.findByShortUrl(request.parameter("hashed-url"));

        if (originalUrl == null) {
            response.status(HttpStatus.NOT_FOUND).body("URL not found.").done();
            return;
        }

        response.status(HttpStatus.OK).body(originalUrl).done();
    }
}
