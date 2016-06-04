package com.evil.clip.http;

import com.evil.clip.domain.Base62Encoder;
import com.evil.clip.domain.UrlRepository;
import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.HttpStatus;

public class ShortenController implements Application {

    private UrlRepository urlRepository;

    public ShortenController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public void handle(Request request, Response response) throws Exception {
        String originalUrl = request.parameter("url");

        if (originalUrl == null) {
            response.status(HttpStatus.OK).done();
            return;
        }


        int base10 = Math.abs(Base62Encoder.toBase10(originalUrl));
        String shortUrl = Base62Encoder.fromBase10(base10);

        urlRepository.add(shortUrl, originalUrl);
        response.status(HttpStatus.OK).body(shortUrl).done();
    }
}
