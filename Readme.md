# Dockerized Springboot Maven application

### Problem statement

In a basic Dockerized Springboot Maven application, develop a single REST endpoint GET /greeting which behaves in a 
manner that fulfills the following criteria:

1. Given the following input values account=personal and id=123 
and the allowable values for account are personal and business
and the allowable values for id are all positive integers
then return "Hi, userId 123".

2. Given the following input values account=business and type=small and 
and the allowable values for account are personal and business
and the allowable values for type are small and big
then return an error that the path is not yet implemented.

3. Given the following input values account=business and type=big and 
and the allowable values for account are personal and business
and the allowable values for type are small and big
then return "Welcome, business user!".

### Single REST endpoint GET /greeting created
1) To get personal account with id [GET]: `http://localhost:5000/greeting/account/personal/id/{id}`.
 e.g. http://localhost:5000/greeting/account/personal/type/123
2) To get business account with type [GET]: `http://localhost:5000/greeting/account/business/type/{type}`.
 e.g. http://localhost:5000/greeting/account/business/type/big

### Solution description
This application is build with maven and can be run with docker or 
docker-compose in root directory. API call will return greeting message 
based on id and type.

### Build application with Maven

 `mvn clean install` 
 
### To run from terminal
 
* Go to project directory and run 

`mvn spring-boot:run`

### Creating docker image and running the application

* To create docker image `docker build -t greeting .`
* To run docker image `docker run -p 5000:5000 greeting`
* To directly start the docker image `docker-compose up` 
