# 🚆 Train Finder

A full-stack Train Finder application that allows users to search for trains between two stations using station codes.

The project consists of a **Spring Boot backend** and a lightweight **HTML, CSS & JavaScript frontend**.

---

## 📌 Project Overview

Train Finder provides a simple interface where users can enter a source and destination station code and search for available trains.

The frontend communicates with the Spring Boot REST API, which processes the search request and retrieves matching train schedules from the database.

### Main Flow

Frontend → REST API → Service Layer → Repository → MySQL Database

---

## ✨ Features

- 🔎 Search trains between two stations
- 🚉 Search using station codes
- 📋 Display train number and train name
- 🕐 Display departure and arrival times
- 📍 Display source and destination station details
- ⏱️ Calculate and display journey duration
- 🔄 Swap source and destination stations
- ⚡ Loading state while searching
- ❌ Error handling for failed API requests
- 📭 No-results state when no trains are found
- ➕ REST API to retrieve all trains
- ➕ REST API to add a train
- 🌐 CORS configuration for frontend-backend communication
- 💾 MySQL database integration
- ⚡ Spring caching configuration

---

# 🛠️ Technologies Used

## Backend

- Java 21
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL
- Lombok
- Spring Boot Actuator
- Maven

## Frontend

- HTML5
- CSS3
- JavaScript
- Fetch API

---

# 📂 Project Structure

```text
Train-Finder/
│
├── Backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/aditya/train/
│   │   │   │       ├── config/
│   │   │   │       ├── controller/
│   │   │   │       ├── entity/
│   │   │   │       ├── repo/
│   │   │   │       └── service/
│   │   │   │
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── application-example.properties
│   │   │
│   │   └── test/
│   │
│   ├── pom.xml
│   ├── mvnw
│   ├── mvnw.cmd
│   └── .gitignore
│
├── Frontend/
│   ├── index.html
│   ├── script.js
│   └── styles.css
│
└── README.md