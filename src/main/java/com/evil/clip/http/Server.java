package com.evil.clip.http;

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

        server.add(new FailureMonitor(toStandardError()));
        server.start(new DynamicRoutes() {{
            get("/").to(new LandingController());
        }});
    }

    public URI uri() {
        return server.uri();
    }

    public void stop() throws IOException {
        server.stop();
    }

}