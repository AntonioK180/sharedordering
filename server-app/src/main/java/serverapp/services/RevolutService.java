package serverapp.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import serverapp.DTO.RevolutDTO;

@Service
public class RevolutService {

    private final String bearerToken = "sk__oXD-qbGSo6TiOIQKFka1X7k4ueZhuAXJ_rThdeuT9bg9iB-MJOj06hdtM7s3WpM";

    public Object callRevolutAPI(RevolutDTO revolutDTO) {
        WebClient.Builder builder = WebClient.builder();

        int amount = revolutDTO.getAmount();
        String currency = revolutDTO.getCurrency();

        WebClient.ResponseSpec retrieve = builder.build()
                .post()
                .uri("https://sandbox-merchant.revolut.com/api/1.0/orders")
                .bodyValue("{\"amount\": " + amount + ", \"currency\": \"" + currency + "\"}")
                .header("Authorization", "Bearer " + bearerToken )
                .retrieve();

        return retrieve.bodyToMono(Object.class).block();
    }
}
