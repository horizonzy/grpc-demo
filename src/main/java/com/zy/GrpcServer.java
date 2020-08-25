package com.zy;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50051;
        Server server = ServerBuilder.forPort(port).addService(new HelloServiceImpl()).build().start();
        System.out.println("Server started, listening on " + port);
        server.awaitTermination();

    }
}
