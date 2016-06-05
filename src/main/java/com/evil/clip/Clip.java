package com.evil.clip;

import com.evil.clip.http.Server;

import java.io.IOException;

public class Clip {

    public static void main(String[] args) throws IOException {
        String hostToBindTo = "0.0.0.0";
        int port = 7575;

        System.out.println("args[0]="+args[0]);
        System.out.println("args[1]="+args[1]);
        if (args.length == 2) {
            //hostToBindTo = args[0];
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
