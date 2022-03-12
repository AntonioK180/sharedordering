package serverapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serverapp.exceptions.OrderNotFoundException;
import serverapp.models.Order;
import serverapp.models.Product;
import serverapp.repositories.OrderRepo;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public OrderService(OrderRepo orderRepo, ProductService productService, UserService userService) {
        this.orderRepo = orderRepo;
        this.productService = productService;
        this.userService = userService;
    }

    public Order getOrderById(Long id) {
        return orderRepo.findOrderById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID: " + id + " cannot be found!"));
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Order addOrder(Order order) {
        Order savedOrder = orderRepo.save(order);
        order.getProducts().stream().forEach(product -> {
            product.setOrder(savedOrder);
            product.setUser(userService.getCurrentUser());
        });
        productService.addMultipleProducts(order.getProducts());
        return getOrderById(savedOrder.getId());
    }

    public Order updateOrder(Long id, Order newOrder) {
        Order orderToUpdate = orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with ID: " + id + " was not found!"));
        orderToUpdate.setCreationDate(newOrder.getCreationDate());
        orderToUpdate.setStoreName(newOrder.getStoreName());
        orderToUpdate.setOrderDate(newOrder.getOrderDate());
        List<Product> products = productService.addMultipleProducts(newOrder.getProducts());
        orderToUpdate.setProducts(products);

        return orderRepo.save(orderToUpdate);
    }

    public void deleteOrder(Long id) {
        Order orderToDelete = orderRepo.findOrderById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID: " + id + " was not found!"));

        orderRepo.delete(orderToDelete);
    }

}
