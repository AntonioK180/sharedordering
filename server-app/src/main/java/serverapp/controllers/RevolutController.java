package serverapp.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.*;


@RestController
@RequestMapping("/api/v1/revolut")
public class RevolutController {

    @GetMapping
    public void initiatePayment() {
        WebClient.Builder builder = WebClient.builder();
        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("amount", 5);
        map.add("currency", "USD");


        ResponseSpec retrieve = builder.build()
                .post()
                .uri("https://merchant.revolut.com/api/1.0/orders")
                .bodyValue("{\n" +
                        "    \"amount\": 5,\n" +
                        "    \"currency\": \"USD\"\n" +
                        "}")
                .header("Authorization", "Bearer sk_nxFIfOdfuHqOE2wUbcja6MkeI1FoFOFjpH5sLlrv070l0CCZJx_l0oNTZTrus8hR" )
                .retrieve();

        System.out.println(retrieve.bodyToMono(String.class).block());

        System.out.println("I am calling the Revolut API!");
    }

}
