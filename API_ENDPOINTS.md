# Documentation of the API endpoints

## Status codes

- 200 OK = Operation 
- 401 Unauthorized = La operación necesita permisos 

## Permited Operations
- Admin
  1. GET/POST/PUT/DELETE Quote
  2. GET/POST/PUT/DELETE Topic
  3. POST USER (Register)
  4. GET/POST/PUT Image (Put with no image is delete)


## Resources

| Resource        | URL           | Operations  |
| --------------- |---------------| ------------|
| Quote           | https://localhost:8443/api/quotes/?page=pageNumber | GET |
| Quote           | https://localhost:8443/api/quotes/id      |   GET/PUT/DELETE |
| Quote           | https://localhost:8443/api/quotes/ |    POST |
| Topic           | https://localhost:8443/api/topics/?page=pageNumber | GET |
| Topic           | https://localhost:8443/api/topics/id      |   GET/PUT/DELETE |
| Topic           | https://localhost:8443/api/topics/ |    POST |
| Topic           | https://localhost:8443/api/topics/pdf/id | GET |
| Image           | https://localhost:8443/api/images/quoteId      |   POST/GET |
| Graph           | https://localhost:8443/api/graph | GET |
| Login           | https://localhost:8443/api/login/     |   GET/POST|
| Logout          | https://localhost:8443/api/logout/ |    GET/POST |

## JSON EXAMPLES



