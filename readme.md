# Dashboard Umroh Backend Application

## Overview
This is the backend application for the Dashboard Umroh system, built with:
- Kotlin
- Spring Boot
- PostgreSQL
- JWT Authentication

## Features
- User management
- Role-based access control
- Umroh package management
- Transaction processing
- Dashboard analytics

## API Documentation
API documentation is automatically generated using Swagger/SpringDoc. Access it at:
`http://localhost:8080/swagger-ui.html` when the application is running.

## Getting Started

### Prerequisites
- Java 17+
- PostgreSQL
- Gradle

### Installation
1. Clone the repository
2. Configure database settings in `application.yaml`
3. Run the application:
```bash
./gradlew bootRun
```

### Environment Variables
```bash
DATABASE_URL=jdbc:postgresql://localhost:5432/umroh_db
DATABASE_USERNAME=your_db_user
DATABASE_PASSWORD=your_db_password
JWT_SECRET=your_jwt_secret
```

### Running with Docker
```bash
docker-compose up --build
```

## Project Structure
```
src/
├── main/
│   ├── kotlin/
│   │   └── co/id/bankbsi/dashboardumroh/
│   │       ├── controller/ - API controllers
│   │       ├── service/ - Business logic
│   │       ├── repository/ - Database access
│   │       ├── model/ - Data models
│   │       └── security/ - Authentication
├── resources/
│   ├── application.yaml - Configuration
```

## Contributing
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## Contact
For support, please contact [your email/contact info]
