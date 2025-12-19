# ✈️ Airline Management System – Backend

A **Spring Boot REST API** for managing airlines, flights, employees, passengers, bookings, schedules, and flight seats with **JWT-based authentication**, **role-based authorization**, and **Dockerized deployment**.

---

## 📌 Features

- 🔐 JWT Authentication & Authorization
  - Passenger & Employee sign-up
  - Secure login with token generation
  - Role-based access (`PASSENGER`, `EMPLOYEE`)
- ✈️ Airline & Flight Management
  - Create, update, activate/deactivate flights
  - View active flights
  - Flights by airline, source, destination
- 👨‍✈️ Employee & Flight Staff Management
  - Employee sign-up
  - Captain & Assistant Captain assignment
  - Employees working for a given airline
- 🧍 Passenger & Booking Management
  - Passenger registration
  - Booking details
  - Advanced JPQL queries
- 💺 Flight Seat Management
  - Seats by airline and seat type
- 🗓️ Schedule Management
  - Flights by source, destination, and date
- 🐳 Dockerized backend application
- 🗄️ MySQL database integration

---

## 🛠️ Tech Stack

| Layer | Technology |
|-----|-----------|
| Language | Java 25 |
| Framework | Spring Boot 3.x |
| Security | Spring Security + JWT |
| Data Access | Spring Data JPA (Hibernate) |
| Database | MySQL 8.0 / H2 (Dev) |
| Build Tool | Maven |
| Containerization | Docker |
| API Testing | Postman |

---

## 🔐 Security Implementation

- JWT token generation and validation
- Custom `JwtFilter` using `OncePerRequestFilter`
- Role-based endpoint authorization
- BCrypt password hashing
- Stateless authentication

---

## 🐳 Docker Setup

### Dockerfile
The project includes a Dockerfile to containerize the Spring Boot application.

### Build Docker Image
```bash
docker build -t airline-backend .
```

### Run Docker Container
```bash
docker run -p 8080:8080 airline-backend
```

### Docker Hub
The image is built and pushed to Docker Hub for easy reuse and deployment.

```bash
docker tag airline-backend <dockerhub-username>/airline-backend
docker push <dockerhub-username>/airline-backend
```

---

## 🗄️ Database Configuration

- Uses **MySQL** as the primary database
- Local database name: `airline_db`
- All **H2-related configurations are commented out**
- Users can switch to H2 or any other DB if required
---
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/airline_db
spring.datasource.username=root
spring.datasource.password=your_password
---
# JPA / Hibernate Configuration

spring.jpa.hibernate.ddl-auto=update
---
# Shows SQL queries executed by Hibernate in the terminal
spring.jpa.show-sql=true

# Formats SQL queries for better readability
spring.jpa.properties.hibernate.format_sql=true

# (Optional) Explicit dialect for MySQL
# spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

---

## ▶️ Running the Project Locally

### Prerequisites
- Java 25
- Maven
- MySQL
- Docker (optional)

### Steps
```bash
git clone https://github.com/<your-username>/airline-management-system-backend.git
cd airline-management-system-backend
mvn clean install
mvn spring-boot:run
```

---

## 🧪 API Testing

- APIs tested using **Postman**
- JWT token passed via:
  ```
  Authorization: Bearer <token>
  ```
- Role-based access verified

---

## 📌 Notes

- This project uses **local MySQL** by default
- Docker users must ensure:
  - MySQL is running
  - Environment variables or DB config is updated accordingly
- H2 database configuration is present but commented out

---

## 🚧 Current Status & Next Steps

- ✅ Backend implementation is complete and stable
- ✅ All core features, security, and database interactions are working as expected
- 🖥️ The next planned step is building the frontend application

### Planned Frontend

- Frontend will be developed using **Angular or React** (final framework selection pending)
- Backend APIs are fully prepared to be consumed by a frontend client
- JWT-based authentication and role-based authorization are already implemented and frontend-ready


## 👨‍💻 Author

**Nikhil**  
Backend Developer | Java | Spring Boot | JWT | Docker

---

⭐ If you like this project, give it a star!
