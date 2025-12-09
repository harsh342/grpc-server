package com.fdj.grpcserver.service;

import com.fdj.grpcserver.UserRequest;
import com.fdj.grpcserver.UserResponse;
import com.fdj.grpcserver.UserGrpc.UserImplBase;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends UserImplBase {

    @Override
    public void addUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        String name = request.getName();
        
        // Build response
        UserResponse response = UserResponse.newBuilder()
                .setMessage("User added successfully")
                .setUser(name)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

