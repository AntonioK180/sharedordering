package serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serverapp.models.Order;
import serverapp.models.Product;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {
    Optional<Order> findOrderById(Long id);

}
