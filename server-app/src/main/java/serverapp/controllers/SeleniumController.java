package serverapp.controllers;

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

        for (Product product : productList) {
             if (product.retrieveStoreName().equals(waterstonesOrder.getStoreName())) {
                waterstonesLinks.add(product);
            }
        }

        if(!waterstonesLinks.isEmpty()) {
            double priceSum = waterstonesOrder.checkLinks(waterstonesLinks);
            System.out.println("All items from Waterstones.com cost: " + priceSum);
            return new ResponseEntity<>(waterstonesLinks, HttpStatus.OK);
        }

        return new ResponseEntity<>(amazonLinks, HttpStatus.OK);
    }

}