# EnergyPulse

**A Spring Boot energy analytics dashboard for monitoring energy consumption and estimated costs.**

EnergyPulse is a web application built with Java and Spring Boot. It provides a simple dashboard where users can enter energy consumption values and view an estimated cost and a consumption status.

The project is part of my Java backend development portfolio and is being developed incrementally to strengthen my skills in Spring Boot, server-side rendering, and backend application design.

## Features

- **Energy consumption input** — enter an energy consumption value in kWh.
- **Estimated cost calculation** — calculate an estimated cost based on the entered consumption.
- **Consumption status** — display a status based on a configurable consumption threshold.
- **Thymeleaf integration** — render dynamic values in the HTML dashboard.
- **Responsive dashboard layout** — present key metrics in a card-based interface.

## Technology Stack

- Java 21
- Spring Boot 3.3.5
- Spring MVC
- Thymeleaf
- Maven
- HTML and CSS
- Git and GitHub

## How It Works

1. The user enters an energy consumption value in kWh.
2. The application processes the value through a Spring MVC controller.
3. The estimated cost is calculated using a configurable price-per-kWh value.
4. The application compares the consumption against a threshold.
5. Thymeleaf renders the results in the dashboard.

**Note:** The current price and dashboard metrics are illustrative demo values. They do not represent live energy prices or data from an external energy provider.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 21
- Git (optional, for cloning the repository)

### Run Locally

1. Clone the repository:

   ```bash
   git clone https://github.com/dobosbrigitta-ctrl/EnergyPulse.git
   ```

2. Open the project directory:

   ```bash
   cd EnergyPulse
   ```

3. Run the application using the Maven Wrapper.

   On Windows:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

   On macOS or Linux:

   ```bash
   ./mvnw spring-boot:run
   ```

4. Open the application in your browser:

   http://localhost:8080/

## Project Structure

```text
EnergyPulse/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/com/energypulse/
│   │   │   ├── EnergyPulseApplication.java
│   │   │   └── HomeController.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   └── templates/
│   │   │       └── dashboard.html
│   │   └── ...
│   └── test/
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Current Scope

The current version focuses on the dashboard UI and basic server-side calculations. It uses illustrative values rather than live energy-provider data.

## Planned Improvements

- Add automated tests for controller and calculation logic.
- Introduce a REST API and API documentation with OpenAPI.
- Integrate a PostgreSQL database where appropriate.
- Add external API integration and error handling.
- Containerize the application with Docker.
- Explore deployment to Microsoft Azure.

These improvements will be implemented incrementally as the project evolves.

## Author

**Brigitta Dobos**
