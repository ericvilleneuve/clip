package com.evil.clip.http;

import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.HttpStatus;

public class LandingController implements Application {

    public LandingController() {
    }

    @Override
    public void handle(Request request, Response response) throws Exception {
        response.status(HttpStatus.OK).done("Hello World!");
    }
}
