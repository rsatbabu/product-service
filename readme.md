https://spring.io/guides/tutorials/spring-security-and-angular-js/

This is a test rest application that connects with the client application

Kafka
======
https://dzone.com/articles/running-apache-kafka-on-windows-os
Run Zookeeper
zkserver
To run Kafka
.\bin\windows\kafka-server-start.bat .\config\server.properties

JPA
====
https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-maven-and-mysql/

Postgres
========
https://dzone.com/articles/bounty-spring-boot-and-postgresql-database

H2
====
https://www.baeldung.com/spring-boot-h2-database

Spring
=====
https://spring.io/guides/gs/accessing-data-jpa/
https://spring.io/guides/tutorials/spring-security-and-angular-js/

Run
===
 - `cUrl http://localhost:8085/processOrderEvents`
 - `cUrl http://localhost:8086/processOrderEventsForProduct`
 - `cUrl http://localhost:8080/processOrderEventsForEmail`


Benefits
========
 - No API Calls
 - Less coupled
 - Service is down
 - Integration test is reduced to 1
 - independent of version changes
 - multiple service - add analytics service
 
 1. Architecture
     order service
     Inventory service
     call services one after the other or parallel
     one of them goes offline, all orders are not processed
     new version of order service is added then the orchestrator has to update
     new service means changes to the orchestrator again
     
     
   This is my attempt to figure out the Event Source architecture and also apply a lil bit of CQRS principles in the project.
   These principles are not new, desktop windows work as event driven, win32 used to have message loop
   browsers do not have multiple threads , they work on event loops, 
   and it was succesfully adopted by nodejs
   
     
 2. Benefits
 
   There will be some challenges
 3. Show the UI
 	1. As a user, I should be able to view the list of products with the product information.  
	2. The user should be able to specify the quantity for each product they wish to put into the shopping cart.  
	3. The user should be able to review their shopping cart to update the quantity or remove the product from the cart.  
	4. The user should be able to go back to view the list of products and add more to their shopping cart.  
	5. The user should be able to submit the order after which an order confirmation page should be displayed.  
	6. The user should be able to close their tab or browser and return to the application with the last state of their shopping cart  
 
 4. what should happen after purchase
   - Order detail has to be created
   - product quantity has to be updated
   - email has to be sent
   - Inventory service has to look at send contact suppliers message
   

 Docker using link with IP Address
======================================
 - Run the command
  `docker run --name shopping-cart -d -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -p 5433:5432  --restart=always postgres`  
 - docker inspect note the ipaddress
 - put the ipaddress in the application.properties for jdbc:postgresql://172.17.0.2:5432/postgres 
 - mvn clean install
 - `docker build -t product-service .`
 - `docker run --name prod --link shopping-cart:shopping-cart product-service` 
 - now the spring boot application running inside the docker container connects with the postgres docker container
 
  Docker using link with docker --name 
======================================
 - Run the command with the name of container as shopping-cart
  `docker run --name shopping-cart -d -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -p 5433:5432  --restart=always postgres`  
 - put the name of container in the application.properties for jdbc:postgresql://shopping-cart::5432/postgres 
 - mvn clean install
 - `docker build -t product-service .`
 - `docker run --name prod --link shopping-cart:shopping-cart product-service` 
 - now the spring boot application running inside the docker container connects with the postgres docker container