package com.evil.clip;

import com.evil.clip.http.Server;

import java.io.IOException;

public class Clip {

    public static void main(String[] args) throws IOException {
        String hostToBindTo = "localhost";
        int port = 7575;
        if (args.length == 2) {
            hostToBindTo = args[0];
            port = Integer.valueOf(args[1]);
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
