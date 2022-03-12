package serverapp.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.*;


@RestController
@RequestMapping("/api/v1/revolut")
public class RevolutController {

    @GetMapping
    public void initiatePayment() {
        WebClient.Builder builder = WebClient.builder();

        System.out.println(builder.build()
                .post()
                .uri("https://merchant.revolut.com/api/1.0/orders")
                .retrieve());

        System.out.println("I am calling the Revolut API!");
    }

}
