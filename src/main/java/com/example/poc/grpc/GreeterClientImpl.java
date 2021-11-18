package com.example.poc.grpc;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GreeterClientImpl {

    @GrpcClient("greeter")
    private com.example.poc.grpc.GreeterGrpc.GreeterBlockingStub greeterBlockingStub;

    public String receiveGreeting(String name) {
        com.example.poc.grpc.Hello.HelloRequest request = com.example.poc.grpc.Hello.HelloRequest.newBuilder()
                .setName(name)
                .build();
        return greeterBlockingStub.sayHello(request).getMessage();
    }
}
