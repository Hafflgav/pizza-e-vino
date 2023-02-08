package hello;

import hello.service.OrderMessageRequest;
import hello.service.SortPizzaOrderService;
import io.camunda.zeebe.spring.client.lifecycle.ZeebeClientLifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ServiceController {

    @Autowired
    private ZeebeClientLifecycle client;

    @Autowired
    SortPizzaOrderService sortCakeOrder;

    @Autowired
    SortPizzaOrderService sortCoffeeOrder;

    @RequestMapping(value = "/orderUp", method = RequestMethod.POST)
    public String index(@RequestBody OrderMessageRequest orderMessageRequest) throws Exception {
        System.out.println("Got this message for Mike: " + orderMessageRequest.orderMessage);
        String orderMessage = orderMessageRequest.orderMessage;
        Map<String, Object> vars = new HashMap<String, Object>();

        vars.put("message", orderMessageRequest.orderMessage.toLowerCase());
        vars.put("businessKey", orderMessageRequest.orderName);

        client.newCreateInstanceCommand()
                .bpmnProcessId("orderProcess")
                .latestVersion()
                .variables(vars)
                .send();

        return "We are working on the order for " + orderMessage;
    }
}
