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
