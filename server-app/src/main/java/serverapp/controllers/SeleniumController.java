package serverapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serverapp.models.Order;
import serverapp.models.Product;
import serverapp.repositories.OrderRepo;
import serverapp.selenium.ProductsValidationDTO;
import serverapp.selenium.StoreURLParser;
import serverapp.selenium.StoreURLParserBuilder;
import serverapp.selenium.amazon.AmazonOrder;
import serverapp.selenium.waterstones.WaterstonesOrder;
import serverapp.services.OrderService;
import serverapp.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/selenium")
public class SeleniumController {
    WaterstonesOrder waterstonesOrder = new WaterstonesOrder();
    AmazonOrder amazonOrder = new AmazonOrder();
    StoreURLParserBuilder storeURLParserBuilder = new StoreURLParserBuilder();

    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public SeleniumController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("waterstones/{id}")
    public ResponseEntity<Order> makeOrder(@PathVariable("id") Long id) {
        Order order = orderService.getOrderById(id);
        System.out.println("I will be ordering from Waterstones!");

        waterstonesOrder.makeAnOrder(order.getProducts());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Product>> checkURLs(@RequestBody ProductsValidationDTO productsValidationDTO) {
        List<Product> productList = productsValidationDTO.getProducts();
        String storeName = productsValidationDTO.getStoreName();

        System.out.println("PRODUCTS: " + productList);
        System.out.println("STORE NAME: " + storeName);

        if (!productList.isEmpty()) {
            ArrayList<Product> invalidProductURLs = new ArrayList<>();
            ArrayList<Product> validProductURLs = new ArrayList<>();
            StoreURLParser storeURLParser = storeURLParserBuilder.getURLParser(storeName);

            System.out.println("Store URL Parser: " + storeURLParser.getClass().toString());

            for (Product product : productList) {
                if (!product.retrieveStoreName().equals(storeURLParser.getStoreName())) {
                    invalidProductURLs.add(product);
                } else {
                    validProductURLs.add(product);
                }
            }

            if (!invalidProductURLs.isEmpty()) {
                invalidProductURLs.stream().forEach(
                        product -> {
                            product.setId((long) -1);
                        }
                );
                return new ResponseEntity<>(invalidProductURLs, HttpStatus.OK);
            } else if (!validProductURLs.isEmpty()) {
                validProductURLs = (ArrayList<Product>) storeURLParser.checkLinks(validProductURLs);
                return new ResponseEntity<>(validProductURLs, HttpStatus.OK);
            }
        }

        return null;
    }

}