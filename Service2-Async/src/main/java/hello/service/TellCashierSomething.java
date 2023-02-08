package hello.service;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component(value = "tellCashierAboutOrder")
public class TellCashierSomething {
    private final RestTemplate restTemplate;

    public TellCashierSomething(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @JobWorker(type = "tellCashierAboutOrder")
    public void tellCashierAboutOrder(String orderMessage, String businessKey) throws Exception {
        URI uri = URI.create("http://localhost:8082/messageForCashier/");
        OrderMessageRequest request = new OrderMessageRequest();
        request.orderMessage = orderMessage;
        request.orderName = businessKey;
        restTemplate.put(uri, request);
        System.out.println(businessKey + " Ready!");
    }
}
