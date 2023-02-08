# Pizza e Vino
This is an example of how to decouple dependent micro-services by adding the Camunda Platform 8  Engine and Kafka as part 
of the architecture. The project was demoed at a talk at the [Java User Group Munich](https://www.meetup.com/de-DE/java-user-group-munchen-jugm/)


There are 3 services:
* a ``checkout``, where you can make orders to 
* a ``service`` that is responsible for serving food and drinks
* and a ``cooking`` service if a pizza needs to be made.


There are two versions of the architecture
 * Synchronous. All communication between services is completed in one transaction per request.
 * Asynchronous. Each request is persisted and completed in a new transaction while waiting for the potential of additional  requests.

## Synchronous Architecture
To start the Synchronous version of this example open and build the following 3 Spring Boot projects
* [Cashier](./Cashier/) - Which will start on http://localhost:8082
* [Cooking](./Barista/) - Which will start on http://localhost:8081
* [Sort Order Sync](./SortOrder-Sync/) - Which will start on http://localhost:8080

Each of these can be started within your IDE as a Spring Boot project. You can also build them and start the jar file in the generated target folder.

Once each is started you can enter orders by going to the [Cashier Homepage](http://localhost:8082).
![knkorder1]

When the submit button is clicked it will send a REST call ``http://localhost:8080/order-up`` to the Order-Sorter service with a payload that includes the order and the name of the person making the order.
If the order contains ``coffee`` then it will call the barista service on `http://localhost:8081/WorkIt/`. Once the coffee is ready it returns to the order sorter, which will return the result to the frontend, which will display ``Order of [orderMessage] Is Ready``





