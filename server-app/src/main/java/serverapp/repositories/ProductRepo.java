package serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serverapp.models.Order;
import serverapp.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByOrder(Order order);
    Optional<Product> findProductById(Long id);
}
