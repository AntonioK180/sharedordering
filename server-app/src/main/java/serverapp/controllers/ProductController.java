package serverapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serverapp.models.Product;
import serverapp.models.User;
import serverapp.repositories.ProductRepo;
import serverapp.services.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductRepo productRepo;

    @Autowired
    private UserService userService;

    @Autowired
    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getCurrentUserProducts() {
        User currentUser = userService.getCurrentUser();
        List<Product> currentUserProducts = productRepo.findByUserId(currentUser.getId());

        return new ResponseEntity<>(currentUserProducts, HttpStatus.OK);
    }

}
