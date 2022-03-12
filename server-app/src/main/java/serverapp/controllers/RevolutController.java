package serverapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/revolut")
public class RevolutController {

    @GetMapping
    public void initiatePayment() {

    }

}
