# A simple Order Management System
1. In this simple project Customer is use to place the orders. We are considering that Products and all other Entities are
   not in use because We need to keep track of	how	much discount is given to which customer and for which order, so 
   that customers can claim money back later.
2. This Simple application maintins the discount cost on each order placed by a Customer.
3. Their is 3 categories of a customer i.e. Regular, Gold, Platinum.
4. Initially the customer is in regular category but 
  4.1. Customer is promoted to gold category when he has placed 10 orders and Gold Category of Customer gets 10% discount.
	4.2. Customer is promoted to Platinum category when he has placed 20 orders and Platinum Category of Customer gets 20% discount.
5. When a customer placed 9 Orders or 19 Orders then the customer will get an email of Promotion to 
   gold promotion or platinum promotion to get benefits of 10% or 20% discount respectively.

Note :- To Run this project we just need to run "mvn clean spring-boot:run" command but before that 
        please change the application.properties file as per you db credentials.
