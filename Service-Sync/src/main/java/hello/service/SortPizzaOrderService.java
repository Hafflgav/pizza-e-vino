package hello.service;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component(value = "sortPizzaOrder")
public class SortPizzaOrderService {
    private final RestTemplate restTemplate;

    public SortPizzaOrderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @JobWorker(type="sortPizzaOrder")
    public void sortPizzaOrder(){
        String message = "Pizza Needed";

        URI uri = URI.create("http://localhost:8081/WorkIt/");
        OrderMessageRequest request = new OrderMessageRequest();
        request.orderMessage = message;
        restTemplate.put(uri, request);
        System.out.println("I've sent an order for Pizza");
    }
}
