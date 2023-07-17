# employee-management
This is an Employee Management Project built using Java 17 and Spring Boot 3.X. It provides a robust and efficient solution for managing employee data within an organization.

# Features
* Employee CRUD Operations: Create, Read, Update, and Delete employee records.
* Employee Search: Search for employees based on various criteria such as name, department, designation, etc.
* Department Management: Create, update, and delete departments.
* Role-based Authentication: Different roles such as Admin and Employee with different access levels.
* User Registration and Login: Register and authenticate users with secure password hashing.
* Employee Reports: Generate reports based on employee data such as department-wise employee count, average salary, etc.

# Technologies Used
* Java
* Spring Boot
* Spring Data JPA
* Spring Security
* MySQL (or any other database of choice)
* Maven (for dependency management)
* Thymeleaf (for server-side rendering)

# Setup Instructions
* Clone the repository:
git clone https://github.com/ssandy431/employee.git

Configure the database connection in the application.properties file:
#MYSQL DB properties
spring.datasource.url=DB_URL<br>
spring.datasource.username=USER_NAME<br>
spring.datasource.password=DB_PASSWORD<br>

#Hibernate Properties
* spring.jpa.open-in-view=false<br>
* spring.jpa.hibernate.ddl-auto=update<br>
* spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect<br>
* spring.jpa.show-sql=true<br>
* spring.jpa.properties.hibernate.format_sql=true<br>

Configure you server property as per your wish. But make sure no other same port is running.<br>
* server.port=9000

Download Redis and extract it. traverse to main folder and make changes in redis.windows.conf uncomment #requirepass and #port. By default redis runs on 6379 without password<br>
Run cmd:  redis-server /path/to/redis.conf<br>
Redis configuration
* redis.host=localhost<br>
* redis.port=REDIS_PORT<br>
* redis.password=REDIS_PWD<br>

# log properties
* logging.level.root=WARN<br>
* logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} %highlight(%-3level) [%thread] %logger{15} - %msg%n<br>
* spring.main.banner-mode=off


		
