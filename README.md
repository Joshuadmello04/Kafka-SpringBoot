# 🚀 Trade Processing Microservices (Spring Boot + Kafka)

## 📌 Overview

This project demonstrates a **microservices architecture** using:

* **Spring Boot**
* **Apache Kafka (KRaft mode – no Zookeeper)**
* **H2 Database**
* **REST APIs**

It consists of **two services**:

### 1️⃣ Account Service

* Provides **CRUD operations** for account data
* Stores data in **H2 in-memory database**
* Exposes REST APIs

### 2️⃣ Trade Service

* Consumes **XML trade messages from Kafka**
* Calls Account Service via REST
* Produces **enriched JSON output**

---

# 🧠 Architecture

```text
Kafka Topic (test-topic)
        ↓
Trade Service (Consumer)
        ↓
REST Call
        ↓
Account Service
        ↓
H2 Database
```

> ⚡ This project uses Kafka in **KRaft mode (no Zookeeper required)**.

---

# ⚙️ Tech Stack

* Java 17
* Spring Boot 3.2.5
* Spring Kafka
* Spring Web
* Spring Data JPA
* H2 Database
* Jackson XML
* Lombok

---

# 📁 Project Structure

## Account Service

```
account-service
 ├── controller
 ├── service
 ├── repository
 ├── entity
 └── application.properties
```

## Trade Service

```
trade-service
 ├── consumer
 ├── service
 ├── client
 ├── dto
 ├── config
 └── application.properties
```

---

# 🧾 Account Service

## 📌 Description

Handles account data with full **CRUD support**.

---

## 🔗 Base URL

```
http://localhost:8080/accounts
```

---

## 📌 Endpoints

### ✅ Create Account

```
POST /accounts
```

**Request Body**

```json
{
  "accountNumber": "123",
  "accountHolderName": "<enter name>",
  "currency": "INR",
  "branch": "Mumbai"
}
```

---

### ✅ Get Account by ID

```
GET /accounts/{accountNumber}
```

**Response**

```json
{
  "accountNumber": "123",
  "accountHolderName": "<enter name>",
  "currency": "INR",
  "branch": "Mumbai"
}
```

---

### ✅ Get All Accounts

```
GET /accounts
```

---

### ✅ Update Account

```
PUT /accounts/{accountNumber}
```

---

### ✅ Delete Account

```
DELETE /accounts/{accountNumber}
```

---

## 🗄️ H2 Database

### Console Access:

```
http://localhost:8080/h2-console
```

**JDBC URL**

```
jdbc:h2:mem:testdb
```

---

# 📥 Trade Service

## 📌 Description

Consumes Kafka messages, enriches them with account data, and logs output.

---

## 🔗 Kafka Topic

```
test-topic
```

---

## 📥 Input (Kafka Message)

XML format:

```xml
<trade>
    <tradeId>T1</tradeId>
    <time>2026-03-23</time>
    <amount>1000</amount>
    <accountNumber>123</accountNumber>
</trade>
```

---

## 🔄 Processing Flow

1. Kafka receives XML message
2. Trade Service consumes message
3. XML → Java object (Jackson XML)
4. Calls Account Service via REST
5. Enriches trade data
6. Outputs enriched JSON

---

## 📤 Output (Console)

```json
{
  "tradeId": "T1",
  "time": "2026-03-23",
  "amount": 1000,
  "accountNumber": "123",
  "accountHolderName": "<enter name>",
  "currency": "INR",
  "branch": "Mumbai"
}
```

---

# ⚙️ Configuration

## Account Service (`application.properties`)

```properties
server.port=8080

spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

---

## Trade Service (`application.properties`)

```properties
server.port=8082

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=trade-group
spring.kafka.consumer.auto-offset-reset=earliest
```

---

# ▶️ How to Run (Kafka KRaft Mode)

## 1️⃣ Start Kafka

```bash
C:\kafka>bin\windows\kafka-storage.bat format -t test-cluster-id -c config\kraft\server.properties
```

> If already formatted:

```bash
--ignore-formatted
```

---

```bash
C:\kafka>bin\windows\kafka-server-start.bat config\kraft\server.properties
```

---

## 2️⃣ Verify Kafka

```bash
kafka-topics.bat --list --bootstrap-server localhost:9092
```

---

## 3️⃣ Create Topic (if needed)

```bash
kafka-topics.bat --create --topic test-topic --bootstrap-server localhost:9092
```

---

## 4️⃣ Start Services

* Start **Account Service** → http://localhost:8080
* Start **Trade Service** → http://localhost:8082

---

## 5️⃣ Send Kafka Message

```bash
kafka-console-producer.bat --topic test-topic --bootstrap-server localhost:9092
```

Paste (single line):

```xml
<trade><tradeId>T1</tradeId><time>2026-03-23</time><amount>1000</amount><accountNumber>123</accountNumber></trade>
```

---

# 🚀 Future Improvements

* WebClient (Reactive REST calls)
* Kafka Retry & Dead Letter Queue (DLQ)
* Centralized Logging
* Dockerization
* API Gateway

---

# 🧠 Key Learnings

* Event-driven architecture using Kafka
* Microservice communication via REST
* XML to Java object mapping
* Data enrichment pipeline
* Clean service-layer architecture

---

# 👨‍💻 Author

**insert name**

---

# ⭐ Support

If you found this useful, consider giving it a ⭐ on GitHub!
