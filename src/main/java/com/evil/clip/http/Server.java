package com.evil.clip.http;

import com.evil.clip.domain.UrlRepository;
import com.evil.clip.view.LandingView;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.middlewares.FailureMonitor;
import com.vtence.molecule.middlewares.FileServer;
import com.vtence.molecule.middlewares.StaticAssets;
import com.vtence.molecule.routing.DynamicRoutes;
import com.vtence.molecule.servers.SimpleServer;

import java.io.File;
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
        server.add(staticAssets());
        server.start(new DynamicRoutes() {{
            get("/").to(new LandingController(new LandingView()));
            get("/shorten").to(new ShortenController(urlRepository));
            get("/expand").to(new ExpandController(urlRepository));
            get("/:hashed-url").to(new ExpandController(urlRepository));
        }});
    }

    private StaticAssets staticAssets() {
        return new StaticAssets(new FileServer(new File("webapp"))).serve("/css", "/scripts");
    }

    public URI uri() {
        return server.uri();
    }

    public void stop() throws IOException {
        server.stop();
    }

}