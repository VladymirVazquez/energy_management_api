# Energy Management API

## Description

Energy Management API is a RESTful backend MVP designed to monitor and analyze energy consumption in an organization, building, zone, or house. 

The system allows users to register infrastructure, record energy measurements, monitor energy consumption, and receive alerts when an anomaly occurs.

The project is currently developed as an MVP using Spring Boot and PostgreSQL, with a future frontend planned using React and TypeScript.

## Features

* Energy consumption monitoring
* Average, maximum, and minimum energy consumption
* Date range filtering
* Anomaly detection
* Automatic alerts
* Soft delete

## Technologies

* Java 25
* Spring Boot 4.1.1
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Bean Validation

## Architecture

The architecture is based on the following sequence:

```text
Controller → Service → Repository → PostgreSQL
```

The API also uses DTOs, Mappers, and a global exception handler to manage the data flow and errors.

## Project Structure

```text
src/
├── controller/
├── service/
├── repository/
├── dto/
│   ├── request/
│   └── response/
├── mapper/
└── exception/
```

## API Endpoints

### Organizations

| Method | Endpoint              | Description                 |
| ------ | --------------------- | --------------------------- |
| GET    | `/organizations`      | Get active organizations    |
| POST   | `/organizations`      | Create an organization      |
| PUT    | `/organizations/{id}` | Modify an organization      |
| DELETE | `/organizations/{id}` | Soft delete an organization |

### Buildings

| Method | Endpoint          | Description            |
| ------ | ----------------- | ---------------------- |
| GET    | `/buildings`      | Get active buildings   |
| POST   | `/buildings`      | Create a building      |
| PUT    | `/buildings/{id}` | Modify a building      |
| DELETE | `/buildings/{id}` | Soft delete a building |

### Zones

| Method | Endpoint      | Description        |
| ------ | ------------- | ------------------ |
| GET    | `/zones`      | Get active zones   |
| POST   | `/zones`      | Create a zone      |
| PUT    | `/zones/{id}` | Modify a zone      |
| DELETE | `/zones/{id}` | Soft delete a zone |

### Devices

| Method | Endpoint        | Description          |
| ------ | --------------- | -------------------- |
| GET    | `/devices`      | Get active devices   |
| POST   | `/devices`      | Create a device      |
| PUT    | `/devices/{id}` | Modify a device      |
| DELETE | `/devices/{id}` | Soft delete a device |

### Energy Readings

| Method | Endpoint                          | Description                                  |
| ------ | --------------------------------- | -------------------------------------------- |
| GET    | `/readings`                       | Get all energy readings                      |
| POST   | `/readings`                       | Register an energy reading                   |
| GET    | `/devices/{id}/readings`          | Get readings by device                       |
| GET    | `/devices/{id}/consumption`       | Get total energy consumption by device       |
| GET    | `/zones/{id}/consumption`         | Get total energy consumption by zone         |
| GET    | `/buildings/{id}/consumption`     | Get total energy consumption by building     |
| GET    | `/organizations/{id}/consumption` | Get total energy consumption by organization |
| GET    | `/devices/{id}/average`           | Get average energy consumption               |
| GET    | `/devices/{id}/max`               | Get maximum energy consumption               |
| GET    | `/devices/{id}/min`               | Get minimum energy consumption               |
| GET    | `/readings/{id}/abnormal`         | Check if a reading is abnormal               |
| GET    | `/devices/{id}/abnormal`          | Get abnormal readings by device              |
| GET    | `/zones/{id}/abnormal`            | Get abnormal readings by zone                |
| GET    | `/zones/{id}/devices`             | Get active devices in a zone                 |
| GET    | `/buildings/{id}/zones`           | Get active zones in a building               |
| GET    | `/organizations/{id}/buildings`   | Get active buildings in an organization      |

The following endpoints support optional date filtering:

```text
/devices/{id}/readings
/devices/{id}/consumption
/zones/{id}/consumption
/buildings/{id}/consumption
/organizations/{id}/consumption
```

Example:

```text
?startDate=2026-09-22T09:00:00&finalDate=2026-09-22T13:00:00
```

### Alerts

| Method | Endpoint               | Description           |
| ------ | ---------------------- | --------------------- |
| GET    | `/alerts`              | Get all alerts        |
| GET    | `/alerts/unresolved`   | Get unresolved alerts |
| PUT    | `/alerts/{id}/resolve` | Resolve an alert      |

## Data Model

```text
Organization
     ↓
Building
     ↓
Zone
     ↓
Device
     ↓
EnergyReading
     |
     └── Alert
```

## Validation and Error Handling

The API uses Bean Validation to validate request data, such as required fields, string length, and positive values.

A global exception handler is used to manage errors and return appropriate HTTP status codes.

## Example Requests

### Create an Organization

```json
{
  "name": "IMSS"
}
```

### Create a Device

```json
{
  "name": "Aire acondicionado 1",
  "type": "Aire acondicionado",
  "zoneId": 1,
  "maxEnergy": 5.0
}
```

### Register an Energy Reading

```json
{
  "deviceId": 1,
  "power": 2.4,
  "energy": 5.8,
  "timestamp": "2026-09-17T12:30:00"
}
```

## Getting Started

### Prerequisites

* Java 25
* Maven
* PostgreSQL
* Git

### Clone the Repository
```bash
git clone https://github.com/VladymirVazquez/energy_management_api.git
cd energy_management_api
```
### Create the PostgreSQL Database
Create a database named:
energy_management

### Configure the Application
Update the PostgreSQL connection settings in:
src/main/resources/application.properties
Example:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/energy_management
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
### Run the Application
```bash
mvn spring-boot:run
```
The API will run by default at:

`http://localhost:8080`

## Future Improvements

* React + TypeScript frontend
* Energy consumption dashboard
* Data visualization
* Authentication and authorization
* Real-time monitoring
* Advanced anomaly detection

## Author

Vladymir Vazquez
- GitHub: [VladymirVazquez](https://github.com/VladymirVazquez)
