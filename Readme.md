
## 🧩 Microservices Overview

### 1. Product Service
- Manages products: CRUD operations, categorization, pricing.
- Exposes REST APIs for product listings and details.
- Database: PostgreSQL / MongoDB

### 2. Order Service
- Manages customer orders, payment status, and order history.
- Handles communication with Product and Inventory services.
- Database: PostgreSQL

### 3. Inventory Service
- Manages stock availability and reservations.
- Automatically updates stock on order events.
- Database: Redis / PostgreSQL

## 🔗 Inter-Service Communication

| Communication Type | Used For                | Tech Stack    |
|--------------------|-------------------------|---------------|
| REST APIs          | External API requests   | Spring Boot / Express |
| Message Queue 📨     | Events: OrderPlaced, StockUpdated | RabbitMQ / Kafka |

> Each service can register with a service discovery system like **Consul** or **Eureka** (optional for future scale).

## 🧰 Tech Stack

- **Java + Spring Boot** / **Node.js + Express**
- **Docker + Docker Compose**
- **RabbitMQ / Kafka** for asynchronous communication
- **PostgreSQL / MongoDB / Redis**
- **Swagger/OpenAPI** for API documentation *(optional if not using Swagger)*

## 🚀 Getting Started

> You must have **Docker**, **Docker Compose**, and optionally **PostgreSQL** locally installed.

1. Clone the repository:
   ```bash
   git clone https://github.com/your-org/ecommerce-microservices.git
   cd ecommerce
   
2. Start services with Docker Compose:

    ```bash
    docker-compose up --build

3. Access services:

- **Product Service: http://localhost:8001

- **Order Service: http://localhost:8002

- **Inventory Service: http://localhost:8003

# ✅ Future Enhancements
- **Add centralized API Gateway (e.g., Kong, Zuul)

- **Implement Authentication Service using OAuth2 / JWT

- **Add Circuit Breakers and Resilience (e.g., Resilience4j)

- **Integrate Prometheus + Grafana for monitoring

### 💡 Modular, decoupled, and production-ready microservices to power the next-gen eCommerce platform.