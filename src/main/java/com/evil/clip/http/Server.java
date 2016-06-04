package com.evil.clip.http;

import com.evil.clip.domain.UrlRepository;
import com.evil.clip.view.LandingView;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.middlewares.FailureMonitor;
import com.vtence.molecule.routing.DynamicRoutes;
import com.vtence.molecule.servers.SimpleServer;

import java.io.IOException;
import java.net.URI;

import static com.evil.clip.http.middleware.PlainErrorReporter.toStandardError;

public class Server {

    private final WebServer server;

    public Server(String host, int port) {
        this.server = new WebServer(new SimpleServer(host, port, 100));
    }

    public void start() throws IOException {

        UrlRepository urlRepository = new UrlRepository("build/urls.db");

        server.add(new FailureMonitor(toStandardError()));
        server.start(new DynamicRoutes() {{
            get("/").to(new LandingController(new LandingView()));
            get("/shorten").to(new ShortenController(urlRepository));
            get("/expand").to(new ExpandController(urlRepository));
        }});
    }

    public URI uri() {
        return server.uri();
    }

    public void stop() throws IOException {
        server.stop();
    }

}