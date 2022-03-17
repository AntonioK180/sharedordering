package serverapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serverapp.models.Order;
import serverapp.models.Product;
import serverapp.repositories.OrderRepo;
import serverapp.selenium.amazon.AmazonOrder;
import serverapp.selenium.waterstones.WaterstonesOrder;
import serverapp.services.OrderService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/selenium")
public class SeleniumController {
    WaterstonesOrder waterstonesOrder = new WaterstonesOrder();
    AmazonOrder amazonOrder = new AmazonOrder();
    private final OrderService orderService;

    @Autowired
    public SeleniumController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("waterstones/{id}")
    public ResponseEntity<Order> makeOrder(@PathVariable("id") Long id) {
        Order order = orderService.getOrderById(id);
        System.out.println("I will be ordering from Waterstones!");

        waterstonesOrder.makeAnOrder(order.getProducts());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Product>> checkURLs(@RequestBody List<Product> productList) {
        ArrayList<Product> amazonLinks = new ArrayList<>();
        ArrayList<Product> waterstonesLinks = new ArrayList<>();
        ArrayList<Product> invalidProductURLs = new ArrayList<>();

        for (Product product : productList) {
             if (product.retrieveStoreName().equals(waterstonesOrder.getStoreName())) {
                waterstonesLinks.add(product);
            } else if (product.retrieveStoreName().equals(amazonOrder.getStoreName())) {
                amazonLinks.add(product);
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

        if (!amazonLinks.isEmpty()) {
            ArrayList<Product> validProducts = (ArrayList<Product>) amazonOrder.checkLinks(amazonLinks);
            System.out.println("I AM CHECKING from amazon!");
            amazonOrder.checkLinks(amazonLinks);
        }

        if (!waterstonesLinks.isEmpty()) {
            ArrayList<Product> validProducts = (ArrayList<Product>) waterstonesOrder.checkLinks(waterstonesLinks);

            validProducts.stream().forEach(product -> {
                System.out.println("Price: " + product.getPrice());
            });

            return new ResponseEntity<>(validProducts, HttpStatus.OK);
        }

        return new ResponseEntity<>(amazonLinks, HttpStatus.OK);
    }

}