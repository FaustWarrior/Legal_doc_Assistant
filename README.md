Legal Document Assistant
This project is a web application designed to help users create and manage legal documents. It features user authentication, document creation, and secure handling of user data using JWT for authorization.

Table of Contents
Features
Technologies Used
Setup and Installation
Usage
Endpoints
Frontend Pages
License
Features
User Registration and Login
JWT Authentication
Create and Retrieve Legal Documents
Secure CORS Configuration
Technologies Used
Backend
Java
Spring Boot
Spring Security
JPA (Hibernate)
MySQL
Frontend
React.js
Axios
React Router
Setup and Installation
Backend Setup
Clone the Repository

bash
Copy code
git clone https://github.com/your-repo/legal-document-assistant.git
cd legal-document-assistant
Configure MySQL Database

Update the application.properties file with your MySQL database credentials.
properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/legaldocassistant
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
Build and Run the Backend

bash
Copy code
./mvnw clean install
./mvnw spring-boot:run
Frontend Setup
Navigate to the Frontend Directory

bash
Copy code
cd frontend
Install Dependencies

bash
Copy code
npm install
Run the Frontend Application

bash
Copy code
npm start
Usage
Register a New User
Endpoint: POST /auth/register
Body:
json
Copy code
{
    "username": "testuser",
    "password": "testpassword"
}
Authenticate a User
Endpoint: POST /auth/authenticate
Body:
json
Copy code
{
    "username": "testuser",
    "password": "testpassword"
}
Response: JWT token
Create a Document
Endpoint: POST /api/documents
Headers:
http
Copy code
Authorization: Bearer <your_jwt_token>
Content-Type: application/json
Body:
json
Copy code
{
    "documentType": "Contract",
    "partyOne": "Alice",
    "partyTwo": "Bob",
    "agreementTerms": "Terms of the agreement"
}
Retrieve Documents
Endpoint: GET /api/documents
Headers:
http
Copy code
Authorization: Bearer <your_jwt_token>
Frontend Pages
App.js
Renders LandingPage.js with options to signup or login.

LandingPage.js
Landing page that directs the user to either the signup or login page.

Signup.js
Handles user registration. Redirects to the landing page on successful registration, otherwise displays an error message.

Login.js
Handles user login. On successful authentication, stores the JWT token and redirects to HomePage.js. Displays an error message on failure.

HomePage.js
Displays a form for creating documents and a button to retrieve documents. Automatically includes the JWT token in the header for authorization.

DocumentForm.js
Form component for creating a new document. Sends the document data to the backend using the JWT token for authorization.

License
This project is licensed under the MIT License. See the LICENSE file for details.
