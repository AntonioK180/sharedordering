package serverapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Size(max = 1024)
    private String url;

    double price;

    Boolean paid;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="order_id", nullable = false)
    Order order;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="user_id", nullable = false)
    User user;

    public Product() {}

    public Product(Long id, String url, double price, Boolean paid) {
        this.id = id;
        this.url = url;
        this.price = price;
        this.paid = paid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
