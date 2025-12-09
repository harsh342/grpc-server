# What to Share with gRPC Clients

## Quick Summary

To enable clients to call your gRPC service, you need to share:

### ✅ 1. Proto File (MANDATORY)
**File:** `proto/User.proto` or `src/main/resources/proto/User.proto`

This is the **most important** file. It defines:
- The service interface (`User` service)
- RPC methods (`AddUser`)
- Request/Response message structures

**Why it's needed:** Clients use this file to generate client stubs in their programming language.

### ✅ 2. Server Endpoint (MANDATORY)
**Information:**
- **Host:** Your server's address
  - Local: `localhost` or `127.0.0.1`
  - Remote: Your public IP or domain (e.g., `grpc.yourdomain.com`)
- **Port:** `9090` (default)

**Format:** `<host>:<port>`
- Example: `localhost:9090`
- Example: `192.168.1.100:9090`
- Example: `api.yourdomain.com:9090`

### 📋 3. Service Details (HELPFUL)
- **Service Name:** `helloworld.User`
- **Method Name:** `AddUser`
- **Request:** `UserRequest` with field `name` (string)
- **Response:** `UserResponse` with fields `message` (string) and `user` (string)

## What Clients Will Do

1. Copy your `User.proto` file
2. Generate client code using their language's gRPC tools
3. Connect to your server using the endpoint you provided
4. Make RPC calls using the generated client

## Example: Sharing Package

Create a zip file or share via repository containing:
```
shared-with-client/
├── User.proto          ← Proto file
└── README.txt          ← Server endpoint info
```

**README.txt content:**
```
gRPC Server Endpoint: localhost:9090
Service: helloworld.User
Method: AddUser

For production, replace localhost with your server's public address.
```

## Security Note

- **Development:** Plaintext connection is OK
- **Production:** Use TLS/SSL and share certificates if needed

