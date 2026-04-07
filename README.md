# Client Server Architecture Works

A comprehensive collection of Java projects focusing on **Client-Server Architecture**, **Socket Programming**, **HTTP Communication**, and **RESTful API Development**. All projects were developed using **NetBeans IDE** and built with **Apache Maven**.

## Repository Overview

This repository contains coursework, tutorials, lab practical exercises, and mock tests covering core concepts in distributed systems and network programming:

- **Socket Programming** (TCP/UDP communication)
- **HTTP Server Implementation**
- **RESTful API Development**
- **Client-Server Communication Patterns**
- **Multi-threaded Network Applications**

---

##  Project Structure

###  Tutorials (Week 01-05)

#### [Tutorial 01-02](Tutorial%2001-02/)
Foundation concepts in client-server architecture
- **tutorial_1**: Basic message sender/receiver implementation
- **Tutorial01**: Extended tutorial with `Sender`, `Receiver`, and `Message` classes

#### [Tutorial 03](Tutorial%2003/)
Socket programming fundamentals
- **Tutorial-week03-socket1**: Basic TCP socket client-server implementation
- **Tutorial-week03-socket2**: Simple chat application with socket communication
- **Tutorial_Week08_EX01**: Advanced socket exercises

#### [Tutorial 04](Tutorial%2004/)
Intermediate networking concepts
- **Tutorial_Week04**: Core networking implementations
- **Tutorial_Week04_Questions**: Practice exercises

#### [Tutorial 05](Tutorial%2005/)
Advanced topics and integrations
- **Tutorial_Week05_EX01**: Complex networking scenarios with JSON support

---

###  Lab-Based Practical Exercises

#### [Lab-Based-Practical-IIT](Lab-Based-Practical-IIT/)
Comprehensive practical exercises covering:
- Socket-based server implementation
- HTTP server implementation
- Request/response handling

#### [Lab-Based-Practical-2025-Mock-Questions](Lab-Based-Practical-2025-Mock-Questions/)
Mock questions for 2025 assessment
- Client-server implementations
- Socket communication patterns

#### [Lab-Based-Practical-CSA-2025-04-HTTP-SOCKET-Questions](Lab-Based-Practical-CSA-2025-04-HTTP-SOCKET-Questions/)
Focused HTTP and Socket protocol exercises

#### [Referral_Deferral_Lab_2025_Questions](Referral_Deferral_Lab_2025_Questions/)
Remedial and referral assessment materials

---

###  Mock Tests

#### [Mock Tests 2026](Mock%20tests%202026/)
Practice assessments for 2026 examination:
- **advanced_mock_exam**: Advanced-level mock assessment
- **Lab_based_practical_Mock**: Practical-based mock questions
- **Lab-Based-Practical-IIT**: IIT-style mock questions
- **MyMockTestSocket**: Custom socket programming mock test
- **Tutorial week 05**: Advanced tutorial mock tests

#### Mock Tests 2025
Similar structure for 2025 assessment preparation

---

###  Coursework

#### [CourseWork 2025 - BooksStore API](CourseWork%202025/BooksStoreAPI/)
**Full-Stack RESTful API Project**

A complete bookstore management system demonstrating professional REST API development with Jersey framework.

**Architecture:**
- **REST Resources** (`src/main/java/com/bookstore/resource/`)
  - `BookResource.java`: Book catalog management
  - `AuthorResource.java`: Author information management
  - `CustomerResource.java`: Customer account management
  - `CartResource.java`: Shopping cart operations
  - `OrderResource.java`: Order processing and management

- **Data Models** (`src/main/java/com/bookstore/model/`)
  - Entity classes for Books, Authors, Customers, Orders, and OrderItems
  - Comprehensive data validation and relationships

**Technology Stack:**
- **Framework**: Jersey REST Framework v2.32
- **Serialization**: Jackson JSON (jersey-media-json-jackson)
- **Dependency Injection**: HK2
- **Deployment**: WAR package for servlet containers
- **Java Version**: Java 8

**Features:**
- Full CRUD operations for books, authors, and customers
- Shopping cart management
- Order processing and tracking
- HTTP-based RESTful communication
- JSON request/response handling

**Building & Running:**
```bash
cd CourseWork\ 2025/BooksStoreAPI
mvn clean install
mvn tomcat:run  # Or deploy to servlet container
```

---

##  Technology Stack

### Core Technologies
| Component | Version | Purpose |
|-----------|---------|---------|
| Java | 8, 17, 19, 21 | Programming language (varies by project) |
| Maven | 3.x | Build automation and dependency management |
| Jersey | 2.32 | REST framework (BooksStore API) |
| JSON | 20231013 | Data serialization (tutorials) |
| JUnit | 4.x | Unit testing framework |

### Development Environment
- **IDE**: NetBeans
- **Build Tool**: Apache Maven
- **Version Control**: Git-compatible

---

##  Key Learning Areas

### 1. **Socket Programming**
   - TCP socket implementation
   - Multi-threaded server handling
   - Client-server communication patterns
   - Message protocols

### 2. **HTTP Protocol**
   - HTTP server implementation from scratch
   - Request/response parsing
   - Header management
   - Status codes and error handling

### 3. **REST Architecture**
   - Resource-oriented design
   - HTTP methods (GET, POST, PUT, DELETE)
   - JSON serialization/deserialization
   - API design best practices

### 4. **Concurrency & Threading**
   - Multi-threaded server implementations
   - Thread synchronization
   - Connection handling

### 5. **Software Design**
   - MVC patterns
   - Separation of concerns
   - Scalability considerations
   - Error handling and validation

---

##  Quick Start

### Prerequisites
- Java 8+ installed
- Maven 3.6+ installed
- NetBeans IDE (optional, for development)

### Building a Project
```bash
cd <project-directory>
mvn clean install
```

### Running a Project
```bash
# For JAR projects
mvn exec:java

# For WAR projects (BooksStore API)
mvn tomcat:run
```

### Running Tests
```bash
mvn test
```

---

##  Project Statistics

| Category | Count |
|----------|-------|
| Total Projects | 21+ |
| Java Classes | 66+ |
| Maven Modules | 21 |
| Tutorial Weeks | 5 |
| Mock Test Suites | 8+ |
| Lab Practicals | 4 |

---

##  Assessment Preparation

### For Examinations
1. Review relevant tutorial week materials
2. Practice corresponding lab-based practical exercises
3. Work through mock tests matching the week/assessment format
4. Reference BooksStore API for REST architecture understanding

### Suggested Learning Path
```
Tutorial 01-02 (Basics)
    ↓
Tutorial 03 (Socket Programming)
    ↓
Tutorial 04 (Intermediate Concepts)
    ↓
Tutorial 05 (Advanced Topics)
    ↓
Lab-Based Practicals (Applied Learning)
    ↓
Mock Tests (Assessment Preparation)
```

---

##  Project Guidelines

All projects in this repository:
-  Follow Maven project structure conventions
-  Include `pom.xml` dependency specifications
-  Have `src/main/java` and `src/test/java` directories
-  Use NetBeans IDE configuration files
-  Compile and run successfully with Maven

---

## 🔗 Common Commands

```bash
# Clean build artifacts
mvn clean

# Compile source code
mvn compile

# Package project
mvn package

# Install to local repository
mvn install

# Run unit tests
mvn test

# Generate project documentation
mvn site
```

---

##  Documentation

Each major project includes:
- **pom.xml**: Dependency and build configuration
- **Source Code**: Well-structured Java classes
- **NetBeans Configuration**: IDE-specific settings
- **Build Artifacts**: Compiled classes and test results

---

##  Learning Outcomes

Upon completing these projects, you will understand:

✓ Client-server architecture design patterns  
✓ Socket-based network programming  
✓ HTTP protocol fundamentals  
✓ RESTful API design and implementation  
✓ Multi-threaded server development  
✓ Java networking APIs (java.net, java.io)  
✓ Maven build and dependency management  
✓ Testing strategies for networked applications  

---

##  Notes

- All projects use Maven for consistent build management
- Java versions vary per project requirements (see individual pom.xml files)
- NetBeans project files are included for IDE compatibility
- Projects are self-contained and can be built independently

---

##  Version Information

- **Repository Last Updated**: April 2026
- **Java Versions**: 8, 17, 19, 21 (depending on project)
- **Maven Version**: 3.6+
- **IDE**: NetBeans

---

