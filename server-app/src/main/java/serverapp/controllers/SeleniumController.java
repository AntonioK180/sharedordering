package serverapp.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serverapp.models.Product;
import serverapp.selenium.waterstones.WaterstonesOrder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/selenium")
public class SeleniumController {
    WaterstonesOrder waterstonesOrder = new WaterstonesOrder();

    @PostMapping
    public ResponseEntity<List<Product>> checkURLs(@RequestBody List<Product> productList) {
        ArrayList<Product> amazonLinks = new ArrayList<>();
        ArrayList<Product> waterstonesLinks = new ArrayList<>();
        ArrayList<Product> invalidProductURLs = new ArrayList<>();
        ArrayList<Product> validProducts = new ArrayList<>();

        for (Product product : productList) {
             if (product.retrieveStoreName().equals(waterstonesOrder.getStoreName())) {
                waterstonesLinks.add(product);
            } else {
                 invalidProductURLs.add(product);
             }
        }

        if (!invalidProductURLs.isEmpty()) {
            invalidProductURLs.stream().forEach(
                    product -> {
                        product.setId((long) -1);
                    }
            );
            return new ResponseEntity<>(invalidProductURLs, HttpStatus.OK);
        }

        if (!waterstonesLinks.isEmpty()) {
            validProducts = (ArrayList<Product>) waterstonesOrder.checkLinks(waterstonesLinks);

            validProducts.stream().forEach(product -> {
                System.out.println("Price: " + product.getPrice());
            });

            return new ResponseEntity<>(validProducts, HttpStatus.OK);
        }

        return new ResponseEntity<>(amazonLinks, HttpStatus.OK);
    }

}