# Client Integration Guide

This document describes what you need to share with clients to enable them to call your gRPC service.

## Required Files and Information

### 1. Proto File (REQUIRED)
The client needs your `.proto` file to generate client stubs in their preferred language.

**File to share:** `src/main/resources/proto/User.proto`

This file contains:
- Service definition (`User` service with `AddUser` RPC method)
- Request/Response message definitions (`UserRequest`, `UserResponse`)

### 2. Server Endpoint Information (REQUIRED)
The client needs to know where your gRPC server is running.

**Information to share:**
- **Host:** Your server's hostname or IP address
  - For local testing: `localhost` or `127.0.0.1`
  - For production: Your public IP or domain name (e.g., `grpc.example.com`)
- **Port:** `9090` (default, configurable in `application.yaml`)

**Example endpoint:** `localhost:9090` or `grpc.example.com:9090`

### 3. Service Details (OPTIONAL but helpful)
- **Service Name:** `helloworld.User`
- **RPC Method:** `AddUser`
- **Request Message:** `UserRequest` (contains `name: string`)
- **Response Message:** `UserResponse` (contains `message: string`, `user: string`)

## What the Client Needs to Do

1. **Copy the proto file** to their project
2. **Generate client stubs** using the appropriate tool for their language:
   - Java: Use `protoc` with `grpc-java` plugin
   - Python: Use `grpcio-tools`
   - Go: Use `protoc-gen-go` and `protoc-gen-go-grpc`
   - Node.js: Use `@grpc/proto-loader` or `grpc-tools`
   - C#: Use `Grpc.Tools` NuGet package
   - etc.

3. **Create a gRPC channel** pointing to your server endpoint
4. **Use the generated client stub** to make RPC calls

## Example Client Connection String

```
<host>:<port>
```

Examples:
- Local: `localhost:9090`
- Remote: `192.168.1.100:9090`
- Production: `api.yourdomain.com:9090`

## Security Considerations

If your gRPC server uses TLS/SSL (recommended for production), you'll also need to share:
- **TLS Certificate** (if using self-signed certificates)
- **Certificate Authority (CA) certificate** (if using custom CA)

For development/testing, plaintext connections are acceptable, but **always use TLS in production**.

## Network Requirements

Ensure the following ports are accessible:
- **Port 9090** (or your configured port) must be open and accessible from the client's network
- Firewall rules should allow inbound connections on this port

## Testing the Connection

Clients can test the connection using tools like:
- **grpcurl** (command-line tool)
- **Postman** (with gRPC support)
- **BloomRPC** (GUI client)
- **Evans** (interactive gRPC client)

Example using grpcurl:
```bash
grpcurl -plaintext localhost:9090 list
grpcurl -plaintext localhost:9090 helloworld.User/AddUser -d '{"name": "John Doe"}'
```

