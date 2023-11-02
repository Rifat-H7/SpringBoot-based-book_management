# SpringBoot-based-book_management

Technologies used:
1. Spring Boot
2. Spring Security
3. JWT role based authentication
4. H2 (in memory database)
5. Partial Global exception handler with validation
6. Add book into collection

How to use:
1. clone this repo and open pom.xml
2. run the application

Postman api testing:
1. user registration:
     method: post 

     url: http://localhost:8080/users/register
     body: {
          "username": string
          "password": string
          "email": string
          "fullName": string
          "roles": "ROLE_ADMIN"/ "ROLE_USER"
     } 

2. Authentication in postman:
     method: post

     url: http://localhost:8080/users/authenticate
     body:  {
          "username": string
          "password": string
     }

   copy the token and set as bearer token in authorization

3. view profile(ADMIN/USER):
     method: get

     url:http://localhost:8080/users/profile

4. add books(Admin):
     method: post

     url: http://localhost:8080/books/add
     body: {
          "name": string
     }

5. view books(no authentication needed):
     method: get

     url: http://localhost:8080/books/list

6. update book(Admin):
     method: put

     url:http://localhost:8080/books/update

7. delete book(Admin):
     method: delete

     url: http://localhost:8080/books/delete
     parameter: id: int

