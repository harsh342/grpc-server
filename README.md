# gRPC Server

Spring Boot gRPC server implementation with Protocol Buffers.

## Quick Start

```bash
mvn spring-boot:run
```

The gRPC server will start on port **9090**.

## For Clients

To integrate with this gRPC server, clients need:

1. **Proto File:** `proto/User.proto` or `src/main/resources/proto/User.proto`
2. **Server Endpoint:** `localhost:9090` (or your server's public address)

See [WHAT_TO_SHARE.md](WHAT_TO_SHARE.md) for detailed information on what to share with clients.

See [CLIENT_INTEGRATION.md](CLIENT_INTEGRATION.md) for comprehensive client integration guide.

## Service Details

- **Service:** `helloworld.User`
- **Method:** `AddUser`
- **Request:** `UserRequest` (name: string)
- **Response:** `UserResponse` (message: string, user: string)
