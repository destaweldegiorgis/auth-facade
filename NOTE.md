### To me, **"production-ready"** means:

- The system can gracefully handle failures and recover from them
- It supports scalability and concurrent access without performance bottlenecks
- Security and observability are embedded from day one
- The codebase is modular, testable, and maintainable
- Deployment and configuration are automated and predictable
- There’s enough documentation and logging to debug any issue quickly


### Key Design Thinking
- Reactive architecture via WebFlux + Project Reactor.
- Token-based auth using a JWT validation filter.
- Fail-safe WebClient for downstream calls with timeout/error handling.
- Minimal controller with clean delegation to service + facade layers.
- Validates input with @Valid, ensuring fail-fast behavior.
  
### Testing JWT with Postman
  A helper class JwtTokenGeneratorTest (under test/util) is included to locally generate JWT tokens for testing the /users/authorize endpoint via Postman.
  
Note: I have also attached my test cURL and postman response screenshot in the README.md description.