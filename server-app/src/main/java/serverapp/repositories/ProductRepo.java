package serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serverapp.models.Product;

public interface ProductRepo extends JpaRepository<Product, Long> { }
