# 📚 Library Management System (Java)

A simple console-based Library Management System built using Core Java.
It demonstrates OOP design, user authentication, wallet handling, and book borrowing workflows.

### 🚀 Features

 * User signup & login
 * Auto-generated user IDs
 * Preloaded list of books
 * Borrow & return with ₹50 security deposit
 * Wallet balance + top-up system
 * Tracks which user borrowed which book
 * Clean menu navigation

### 🛠 Tech Used

 * Java
 * OOP (Classes, Objects, Encapsulation)
 * Console UI (Scanner)

### 🖥 How It Works (Flow)

1. User signs up or logs in

2. After login, user gets menu options:
    * View books
    * Borrow a book
    * Return a book
    * Check wallet / top-up
3. When borrowing:
    * System checks availability
    * Wallet must have ≥ ₹50
4. When returning:
    * Deposit is refunded

### 📝 Sample Output
```
Welcome to My First Library

1) Sign up  2) Login  3) Exit
Choose:
```
Borrowing example:
```
Available books:
[1] "Psychology of Money" by Morgan Housel
...

Enter book id to borrow:
Borrowed. Deposit ₹50.0 deducted.
```
### 🎯 Highlights

 * Fully functional borrowing/returning workflow
 * Deposit deduction + refund logic
 * Simple in-memory data management
 * Great starter project to learn real-world Java logic

### 📂 Project Files
```
├── Book.java           -- Handles book properties & borrow/return logic
├── User.java           -- Manages wallet, borrowed list, authentication
└── LibraryApp.java     -- Main application with menus & operations
```
### 🙌 Acknowledgements
Thanks for exploring this project!

### 📄 License
This project is open-source and available under the MIT License.

### About Me : 
Vinoothna Ramagiri – Electronics & Communication Engineering Graduate | Hyderabad

[![Email](https://img.shields.io/badge/-Email-red?logo=gmail&logoColor=white)](mailto:ramagirivinoothna@gmail.com)

[![LinkedIn](https://img.shields.io/badge/-LinkedIn-blue?logo=linkedin&logoColor=white)](https://www.linkedin.com/in/ramagirivinoothna)
