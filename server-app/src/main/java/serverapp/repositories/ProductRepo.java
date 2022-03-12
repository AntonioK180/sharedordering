package serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serverapp.models.Order;
import serverapp.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long id);
    List<Product> findByUserId(Long userId);
}
