# 🛒 CSC212 E-Commerce Inventory & Order Management System

### 📘 Project Overview
This project is part of the **CSC212 – Data Structures** course.  
The goal is to implement a simplified **E-Commerce Inventory and Order Management System** using **Java**, focusing on applying fundamental **data structure concepts**.

---

## 🎯 Objective
To build a console-based Java system that allows:
- Managing **products, customers, orders, and reviews**.
- Handling **search, insertion, and update** operations.
- Generating **reports** such as top-rated products and order history.
- Applying **time and space complexity analysis** for every operation.

---

## 🧩 Features & Functional Requirements
### 🛍️ Products
- Add / Remove / Update products  
- Search by ID or Name (Linear Search)  
- Track out-of-stock products  

### 👤 Customers
- Register new customer  
- Place a new order  
- View order history  

### 📦 Orders
- Create / Cancel orders  
- Update order status  
- Search order by ID  

### ⭐ Reviews
- Add / Edit review  
- Calculate average rating per product  

### 📊 Business Insights
- Extract reviews by specific customer  
- Suggest **Top 3 Products** by average rating  
- Show all orders between two dates  
- Compare two customers’ reviewed products (with rating > 4)

---

## 🧠 Data Structures Implemented
All data structures are **manually implemented** — no built-in Java collections (`ArrayList`, `HashMap`, etc.) were used.

| Structure | Purpose |
|------------|----------|
| `ProductList` | Stores and manages products |
| `CustomerList` | Stores all customers |
| `OrderList` | Keeps all orders for each customer |
| `ReviewList` | Stores all product reviews (Linked List) |

---


## 📌 UML Class Diagram

![UML Diagram](https://raw.githubusercontent.com/SaraAljuraybah/CSC212-Ecommerce-Project/c38ce69758c5f2a5681cebc1756f3042d4f16815/UML.png)
