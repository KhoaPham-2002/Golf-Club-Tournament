# Golf Club Member and Tournament API

This project is a **Spring Boot** RESTful web application designed to manage **golf club members** and **tournaments**. It provides endpoints for performing **CRUD operations**, searching records, and managing associations between members and tournaments.

The application is backed by a **MySQL database hosted on AWS RDS**, and can optionally be containerized using **Docker** for deployment.

---

## Learning Objectives

- Use Spring Boot to create RESTful APIs  
- Understand and implement many-to-many relationships with JPA  
- Build and run containerized applications using Docker  
- Apply clean coding practices and architecture  
- Use Postman to test API functionality  
- Export API documentation as screenshots 
- Apply AWS to connect DB with RDS  

---

## Technologies Used

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL** (via AWS RDS)
- **Docker** (optional)
- **Lombok** (for cleaner code)
- **Postman** (for API testing)

---

## Features

### Members

- Create new members
- View member details
- Update member records
- Delete members
- Search members by:
  - Name
  - Email
  - Phone number

#### Member Fields:
- ID (auto-generated)
- Name
- Address
- Email
- Phone Number
- Membership Start Date
- Membership Duration (in months)

---

### Tournaments

- Create new tournaments
- View tournament listings
- Update tournament details
- Delete tournaments
- Search tournaments by:
  - Start date
  - Location
- Associate members with tournaments

#### Tournament Fields:
- ID (auto-generated)
- Start Date
- End Date
- Location
- Entry Fee
- Cash Prize

---

## API Endpoints

`GET /members` - Retrieve all members
`GET /members/{id}` - Retrieve a specific member by ID
`POST /members` - Create a new member
`PUT /members/{id}` - Update an existing member
`POST /members/{memberId}/tournaments/{tournamentId}` - Add a tournament to a member
`GET /members/member_search?name=...&email=...&phone=...` - Search members
`GET /tournaments` - Retrieve all tournaments
`GET //{id}/members` - Retrieve all members in tournament
`GET /tournaments/{id}` - Retrieve a specific tournament by ID
`POST /tournaments` - Create a new tournament
`PUT /tournaments/{id}` - Update a tournament
`DELETE /tournaments/{id}` - Delete a tournament
`POST /tournaments/{tournamentId}/members/{memberId}` - Add a member to a tournament
`GET /tournaments/tournament_search?start_date=...&tournament_location=...` - Search tournaments

---

### Prerequisites

- Docker installed
- Docker Compose (`docker-compose.yml`)
- MySQL RDS or local instance running (update `application.properties` accordingly if for AWS)

---

## ✍️ Author

**Khoa Pham**  

