# Auth-Facade

A reactive Spring Boot microservice designed to evaluate user authorization requests in a secure and production-ready manner. This service acts as a facade to downstream authorization logic and applies modern engineering best practices.


##  Features

- **Reactive Architecture** using Spring WebFlux & Project Reactor
- **JWT-Based Authentication** via filter-based token validation
- **Input Validation** using Jakarta `@Valid`
- **Resilient WebClient** for downstream integration
- **Structured Logging** for observability and traceability
- **Unit Tests** with JUnit & Mockito
- **Graceful Error Handling** for downstream service failures
- **Production-Ready Code Practices** including clean architecture and separation of concerns

##  Project Structure
src/
├── main/
│   └── java/com.apple.auth.authfacade/
│       ├── config/         # Security and app-level configurations
│       ├── controller/     # REST controller(s)
│       ├── dto/            # AuthRequest & AuthResponse DTOs
│       ├── exception/      # Global and custom exception handling
│       ├── facade/         # Facade interfaces
│       ├── filter/         # JWT Filter implementation
│       ├── model/          # Model classes
│       ├── repository/     # Downstream client abstraction
│       ├── service/        # Service layer with business logic
│       ├── util/           # Utility classes like JwtUtil
│       └── AuthFacadeApplication.java
│
├── test/
│   └── java/com.apple.auth.authfacade/
│       ├── controller/     # Controller-level tests
│       ├── facade/impl/    # Facade implementation tests
│       ├── service/impl/   # Service implementation tests
│       ├── repository/     # Mock and integration tests
│       └── util/           # JwtTokenGeneratorTest for Postman
│
└── resources/              # application.yml, logback configs, etc.

## Running the Project
mvn clean install

## API Endpoint:
- POST /users/authorize
- Content-Type: application/json
- Authorization: Bearer <JWT_TOKEN>

## Sample Body:
{
"userId": "123",
"action": "read",
"resource": "document-456"
}

## Production Readiness
This project demonstrates production-readiness via:
- Load-ready design using non-blocking WebClient and reactive stack
- Observability: structured Slf4j logging with correlation
- Error handling: fallback for downstream service failures

## Security:
- Input validation (@Valid)
- JWT filter with role/claim verification
- Unauthorized requests result in 401/403

## How to Generate JWT for Testing

To simplify Postman testing, we’ve included:

JwtTokenGeneratorTest (under test/java/com.apple.auth.authfacade.util)
This test class generates a valid JWT token locally so that you can easily copy and use it in your Authorization: Bearer <token> header.

### Sample cURL

curl --location 'http://localhost:8080/users/authorize' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwic3ViIjoidGVzdC11c2VyIiwiaWF0IjoxNzUyODUzOTI0LCJleHAiOjE3NTI4NTQ1MjR9.tck-OVh1M6Mk6vRJvUeZMEo7njiHhrYxQb3NrDb3EVQ' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=7AE87E0BEE2EBA14F8B42077CA3D8838' \
--data '{
"userId": "123",
"action": "read",
"resource": "document-456"
}'

### Sample test screenshot 
![img.png](img.png)