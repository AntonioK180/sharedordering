package serverapp.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.*;
import springfox.documentation.spring.web.json.Json;


@RestController
@RequestMapping("/api/v1/revolut")
public class RevolutController {

    private final String bearerToken = "sk__oXD-qbGSo6TiOIQKFka1X7k4ueZhuAXJ_rThdeuT9bg9iB-MJOj06hdtM7s3WpM";

    @PostMapping
    public ResponseEntity<Object> initiatePayment(@RequestBody RevolutDTO revolutDTO) {
        WebClient.Builder builder = WebClient.builder();

        int amount = revolutDTO.getAmount();
        String currency = revolutDTO.getCurrency();

        System.out.println("AMOUNT: " + amount +
                "\nCURRENCY: " + currency);

        ResponseSpec retrieve = builder.build()
                .post()
                .uri("https://sandbox-merchant.revolut.com/api/1.0/orders")
                .bodyValue("{\"amount\": " + amount + ", \"currency\": \"" + currency + "\"}")
                .header("Authorization", "Bearer " + bearerToken )
                .retrieve();

        Object response = retrieve.bodyToMono(Object.class).block();
        System.out.println(response);

        System.out.println("I am calling the Revolut API!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
