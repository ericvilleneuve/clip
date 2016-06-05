package com.evil.clip;

import com.evil.clip.http.Server;

import java.io.IOException;

public class Clip {

    public static void main(String[] args) throws IOException {
        String hostToBindTo = "0.0.0.0";
        int port = 7575;

        String systemPort = System.getenv("PORT");
        if (systemPort != null) {
            port = Integer.valueOf(systemPort);
        }
        Server server = launch(hostToBindTo, port);
        System.out.println("server is started on " + server.uri());
    }

    public static Server launch(String hostToBindTo, int port) throws IOException {

        Server server = new Server(hostToBindTo, port);
        server.start();
        return server;
    }
}
