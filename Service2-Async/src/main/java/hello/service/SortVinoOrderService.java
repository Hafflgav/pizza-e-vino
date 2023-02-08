package hello.service;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

@Component(value = "sortVinoOrder")
public class SortVinoOrderService {

    long waitTimeMillis = 1000;

    @JobWorker(type = "sortVinoOrder", fetchAllVariables = true)
    public void sortVinoOrder(String message) throws Exception {
        if(message.toLowerCase().contains("vino")){
            int loops = 3;
            System.out.println("We're Working on getting you Vino! -- "+ message );
            while(loops >= 0) {
                System.out.print("..");
                Thread.sleep(waitTimeMillis);
                loops--;
            }
            System.out.println();
            System.out.println("Vino is up -- "+ message );
        }
        System.out.println("All good - order is complete");
    }
}
