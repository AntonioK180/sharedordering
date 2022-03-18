package serverapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serverapp.exceptions.OrderNotFoundException;
import serverapp.exceptions.ProductNotFoundException;
import serverapp.models.Product;
import serverapp.repositories.ProductRepo;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> addMultipleProducts(List<Product> products) {
        return productRepo.saveAll(products);
    }

    public Product updateProduct(Long id, Product product) {
        Product oldProduct = productRepo.findProductById(id).orElseThrow(() -> new OrderNotFoundException("Product with ID: " + id + " was not found!"));

        oldProduct.setPrice(product.getPrice());
        oldProduct.setDisplayName(product.getDisplayName());
        oldProduct.setPaid(product.getPaid());

        return productRepo.save(oldProduct);
    }

    public Product getProductById(Long id) {
        return productRepo.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID: " + id + " cannot be found!"));
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public void deleteProduct(Long id) {
        Product productToDelete = productRepo.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID: " + id + " cannot be found!"));
    }
}
