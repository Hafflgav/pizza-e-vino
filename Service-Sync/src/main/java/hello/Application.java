package hello;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

import java.security.Provider;
import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
@EnableZeebeClient
@Deployment(resources = {"classpath:/orderProcess.bpmn"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
