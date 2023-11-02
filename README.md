
# 📔 BOOK-STORE
![img_1.png](src/main/resources/util/img_1.png)

### 📄 DESCRIPTION

This demo-project is a web-based application designed as a book shopping platform.

The platform offers a diverse collection of books from various categories, allowing users to explore and purchase books. It is designed to provide an exceptional book shopping experience to users based on their role.

The project combines Java-based backend technologies, including the Spring Framework, JWT, and Docker, to ensure efficient data management and secure user interactions.

### 💻 Features

| HTTP Method | Endpoint                          | Role(s)      | Description                                   |
|-------------|----------------------------------|--------------|-----------------------------------------------|
| POST        | /register                         | (all)        | Register a new user.                          |
| POST        | /login                            | (registered) | Log in as a registered user.                  |
| GET         | /books                            | (user)       | Get all books.                                |
| GET         | /books/{id}                       | (user)       | Get a book by ID.                             |
| POST        | /books                            | (admin)      | Create a new book.                            |
| PUT         | /books                            | (admin)      | Update a book.                                |
| GET         | /books                            | (user)       | Search for books with parameters.             |
| DELETE      | /books/{id}                       | (admin)      | Delete a book by ID.                         |
| GET         | /categories                       | (user)       | Get all categories.                           |
| POST        | /categories                       | (admin)      | Create a new category.                        |
| PUT         | /categories/{id}                  | (admin)      | Update a category.                            |
| GET         | /categories/{id}                  | (user)       | Get a category by ID.                         |
| GET         | /categories/{id}/books            | (user)       | Get books by category ID.                    |
| DELETE      | /categories/{id}                  | (admin)      | Delete a category by ID.                     |
| GET         | /cart                             | (user)       | Get the shopping cart.                       |
| POST        | /cart                             | (user)       | Add a book to the shopping cart.             |
| PUT         | /cart/cart-items/{cartItemId}     | (user)       | Update the quantity of a book in the cart.   |
| DELETE      | /cart/cart-items/{cartItemId}     | (user)       | Delete a book from the shopping cart.        |
| GET         | /orders                           | (user)       | Retrieve the user's order history.            |
| POST        | /orders                           | (user)       | Place an order.                               |
| PATCH       | /orders/{orderId}                 | (admin)      | Update the order status.                     |
| GET         | /orders/{orderId}/items           | (user)       | Retrieve all order items for a specific order. |
| GET         | /orders/{orderId}/items/{itemId}  | (user)       | Retrieve a specific order item within an order. |

### 📂 Project
<pre>
├───java
│   └───com.example.book-app
│       ├───config             ---- Application configuration classes
│       ├───controller         ---- HTTP controllers
│       ├───dto                ---- Data transfer objects for HTTP requests and responses
│       ├───exception          ---- Global exception handler and custom exceptions
│       ├───lib                ---- Custom validators for email and password confirmation
│       ├───mapper             ---- Classes for mapping entities
│       ├───model              ---- Entity classes used in the application
│       ├───repository         ---- Classes for CRUD operations with the database
│       ├───security           ---- Security-related classes
│       └───service            ---- Classes providing business logic
├───resources
│   ├───db
│   │   └───changelog         ---- Files for managing the database with Liquibase
│   └───application.properties ---- Application configuration
├───other files
├───pom.xml            ---- Maven configuration
├───checkstyle.xml     ---- Checkstyle rules
├───.env               ---- Database connection credentials
├───Dockerfile         ---- Docker configuration
└───docker-compose.yml ---- Docker Compose configuration
</pre>

### 🔨 Used Technologies
- Java 17
- Spring Boot, Spring Security, Spring data JPA
- MySQL, Docker, Maven, Swagger

### Getting Started
1. Ensure you have Docker installed on your system.
2. Configure your database settings in the [.env](.env) file.
3. Open a terminal and navigate to the root directory of your project.
4. Run the application using Docker Compose: `docker-compose up`
5. Explore the endpoints using tools like Postman or Swagger.

# Authors
#### Viktoriia Svidovska