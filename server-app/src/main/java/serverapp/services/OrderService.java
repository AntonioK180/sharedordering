package serverapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serverapp.exceptions.OrderNotFoundException;
import serverapp.models.Order;
import serverapp.repositories.OrderRepo;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order addOrder(Order order) {
        return orderRepo.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepo.findOrderById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID: " + id + " cannot be found!"));
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public void deleteOrder(Long id) {
        Order orderToDelete = orderRepo.findOrderById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID: " + id + " cannot be found!"));

        orderRepo.delete(orderToDelete);
    }
}
