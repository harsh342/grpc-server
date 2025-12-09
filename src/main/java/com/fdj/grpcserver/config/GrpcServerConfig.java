package com.fdj.grpcserver.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fdj.grpcserver.service.UserServiceImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PreDestroy;

/**
 * Configuration class for gRPC server.
 * Implements CommandLineRunner to start the gRPC server after Spring Boot context is fully initialized
 * and keep the application running by blocking on server.awaitTermination().
 */
@Component
public class GrpcServerConfig implements CommandLineRunner {

    @Value("${grpc.server.port:9090}")
    private int grpcPort;

    @Autowired
    private UserServiceImpl userService;

    private Server server;

    /**
     * Starts the gRPC server and blocks until shutdown.
     * This method runs after Spring Boot context is fully initialized.
     */
    @Override
    public void run(String... args) throws Exception {
        start();
        blockUntilShutdown();
    }

    /**
     * Starts the gRPC server on the configured port.
     */
    private void start() throws IOException {
        server = ServerBuilder.forPort(grpcPort)
                .addService(userService)
                .build()
                .start();
        
        System.out.println("gRPC Server started on port: " + grpcPort);
        
        // Add shutdown hook for graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server...");
            stop();
        }));
    }

    /**
     * Stops the gRPC server gracefully.
     * Called during Spring Boot shutdown via @PreDestroy.
     */
    @PreDestroy
    public void stop() {
        if (server != null && !server.isShutdown()) {
            server.shutdown();
        }
    }

    /**
     * Blocks the current thread until the server is terminated.
     * This keeps the Spring Boot application running.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}

