package com.zy;

import com.example.grpc.gencode.HelloRequest;
import com.example.grpc.gencode.HelloResponse;
import com.example.grpc.gencode.HelloServiceGrpc;
import com.example.grpc.gencode.HelloServiceGrpc.HelloServiceBlockingStub;
import com.example.grpc.gencode.HelloServiceGrpc.HelloServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Iterator;

public class GrpcClient {

    public static void main(String[] args) throws IOException {

        ManagedChannel channel;

        channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

        HelloServiceStub stub = HelloServiceGrpc.newStub(channel);

        StreamObserver<HelloRequest> request = stub.hello(new StreamObserver<HelloResponse>() {
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

        request.onNext(HelloRequest.newBuilder().setFirstName("111").setLastName("111").build());
        request.onNext(HelloRequest.newBuilder().setFirstName("222").setLastName("222").build());
        request.onNext(HelloRequest.newBuilder().setFirstName("3333").setLastName("333").build());

        System.in.read();

        request.onCompleted();

        System.in.read();
    }

}
