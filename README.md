# EasyShop 🛒

A Java Spring-based e-commerce backend that supports product management, category browsing, shopping cart functionality, and user profiles.

## 📌 Project Overview

EasyShop is a simple, modular shopping platform designed with a layered architecture using Spring Boot. It allows users to browse products, organize them into categories, manage their shopping cart, and access profile information.

This project is built from the ground up with an emphasis on clarity, maintainability, and best practices for backend development.

## 🚧 Development Timeline

The following timeline outlines key milestones in the development of EasyShop based on commit activity:

## ✅ June 20, 2025 — Project Initialization

First Commit

Laid the groundwork for the project structure and core components.

## 🛠️ June 23, 2025 — Building Category Features

Completed MySQLCategoryDAO for database interaction.

Finalized CategoriesController to expose category-related endpoints.

Began development of MysqlCartDAO for cart persistence.

Resolved minor bugs improving initial stability.

🛒 June 24, 2025 — Shopping Cart Support

Fully implemented MysqlCartDAO to handle cart operations.

Completed cart controller to manage cart endpoints (add, update, retrieve, etc.).

## 📦 June 25, 2025 — User and Cart Enhancements

Finished development of the ShoppingCartController.

Added getById() functionality and other essential methods.

Completed the update method to modify cart items efficiently.

Developed the ProfileController to expose user profile data.

## 🔧 Technologies Used

Java 17

Spring Boot

JDBC Template

MySQL

REST API

Maven

## 🚀 How to Run

Clone the repository:

git clone https://github.com/knguyen-1228/EasyShop.git
cd EasyShop

Configure your database connection in application.properties.

Run the application:

./mvnw spring-boot:run

Use Postman or a frontend client to interact with the exposed endpoints (e.g., /products, /categories, /cart, /profile).

## 📂 Project Structure Highlights

├── controllers
│   ├── CategoriesController.java
│   ├── ShoppingCartController.java
│   └── ProfileController.java
├── dao
│   ├── ProductDao.java
│   ├── CategoryDao.java
│   └── ShoppingCartDao.java
├── models
│   ├── Product.java
│   ├── Category.java
│   └── ShoppingCartItem.java
├── data
│   └── MySQL implementations for DAOs
└── application.properties

## Interesting Piece of Code
This code was interesting to me because the sql statement was something I enjoyed learning about!
```
@Override
    public void addProduct(int userId, int productId) {
        String sql = """
                INSERT INTO shopping_cart (user_id, product_id, quantity)
                VALUES (?,?,1)
                ON DUPLICATE KEY 
                UPDATE quantity = quantity + 1
                """;

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,userId);
            statement.setInt(2,productId);
            statement.executeUpdate();

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
```

🙌 Author

Kevin Nguyen💼 GitHub: knguyen-1228

