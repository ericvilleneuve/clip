package com.evil.clip.http;

import com.evil.clip.view.LandingView;
import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.HttpStatus;

public class LandingController implements Application {

    private LandingView view;

    public LandingController(LandingView view) {
        this.view = view;
    }

    @Override
    public void handle(Request request, Response response) throws Exception {
        response.status(HttpStatus.OK).body(view.render()).done();
    }
}
