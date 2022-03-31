package serverapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.*;
import serverapp.DTO.RevolutDTO;
import serverapp.services.RevolutService;


@RestController
@RequestMapping("/api/v1/revolut")
public class RevolutController {

    private final String bearerToken = "sk__oXD-qbGSo6TiOIQKFka1X7k4ueZhuAXJ_rThdeuT9bg9iB-MJOj06hdtM7s3WpM";
    private final RevolutService revolutService;

    public RevolutController(RevolutService revolutService) {
        this.revolutService = revolutService;
    }


    @PostMapping
    public ResponseEntity<Object> initiatePayment(@RequestBody RevolutDTO revolutDTO) {

        Object response = revolutService.callRevolutAPI(revolutDTO); //retrieve.bodyToMono(Object.class).block();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
