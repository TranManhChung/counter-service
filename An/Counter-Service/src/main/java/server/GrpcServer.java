package server;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String args[]){
        Server server = ServerBuilder
                .forPort(8080)
                .addService((BindableService) new CounterServiceImpl())
                .build();

        try {
            server.start();
        } catch (IOException e) {
                e.printStackTrace();
        }
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}