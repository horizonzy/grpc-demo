package com.zy;

import com.example.grpc.gencode.HelloRequest;
import com.example.grpc.gencode.HelloResponse;
import com.example.grpc.gencode.HelloServiceGrpc;
import com.example.grpc.gencode.HelloServiceGrpc.HelloServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class GrpcClient {

    public static void main(String[] args) throws IOException {
        ManagedChannel channel = null;
        channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
        HelloServiceStub stub = HelloServiceGrpc.newStub(channel);

        stub.hello(HelloRequest.newBuilder().setFirstName("horizon").setLastName("zhao").build(), new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse value) {
                System.out.println(value.getGreeting());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });

        System.in.read();
    }

}
