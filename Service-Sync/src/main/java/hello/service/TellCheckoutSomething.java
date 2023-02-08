package hello.service;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component(value = "tellCheckoutAboutOrder")
public class TellCheckoutSomething {
    private final RestTemplate restTemplate;

    public TellCheckoutSomething(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @JobWorker(type = "tellCheckout", fetchAllVariables = true)
    public void tellCheckout(@Variable String message, @Variable String businessKey) throws Exception {
        URI uri = URI.create("http://localhost:8082/messageForCheckout/");
        OrderMessageRequest request = new OrderMessageRequest();
        request.orderMessage = message;
        request.orderName = businessKey;
        restTemplate.put(uri, request);
        System.out.println(businessKey + " Ready!");
    }
}
