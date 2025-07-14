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

1. Clone the repository:
```bash
git clone https://github.com/your-repo/dashboardumroh.git
cd dashboardumroh
```

2. Set up PostgreSQL database:
```bash
createdb umroh_db
```

3. Configure database settings in `application.yaml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/umroh_db
    username: your_db_user
    password: your_db_password
```

4. Build and run the application:
```bash
./gradlew build
./gradlew bootRun
```

5. Verify the application is running by accessing:
```bash
http://localhost:8080/api/health
```

6. Access Swagger documentation at:
```bash
http://localhost:8080/swagger-ui.html
```


### Environment Variables

Create a `.env` file in the root directory with:

```bash
# Database configuration
DATABASE_URL=jdbc:postgresql://localhost:5432/umroh_db
DATABASE_USERNAME=your_db_user
DATABASE_PASSWORD=your_db_password

# JWT Configuration
JWT_SECRET=your_jwt_secret
JWT_EXPIRATION=86400000  # 24 hours in milliseconds

# Application Configuration
SERVER_PORT=8080
DEBUG=true
```

Then run:
```bash
source .env
```


### Running with Docker

1. Build and start the containers:
```bash
docker-compose up --build
```

2. Verify the application is running:
```bash
docker ps
```

3. Access the application:
```bash
http://localhost:8080
```

4. To stop the containers:
```bash
docker-compose down
```

5. To view logs:
```bash
docker-compose logs -f
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
