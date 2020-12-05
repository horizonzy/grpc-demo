package com.zy;

import com.example.grpc.gencode.HelloRequest;
import com.example.grpc.gencode.HelloResponse;
import com.example.grpc.gencode.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public StreamObserver<HelloRequest> hello(StreamObserver<HelloResponse> responseObserver) {
        return new StreamObserver<HelloRequest>() {

            String all;

            @Override
            public void onNext(HelloRequest request) {
                all += request.getFirstName() + " " + request.getLastName();
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(HelloResponse.newBuilder().setGreeting(all).build());
                responseObserver.onCompleted();
            }
        };
    }
}
