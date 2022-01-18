package serverapp.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import javax.validation.constraints.Size;

@Entity
@Table(name = "shared_orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private String storeName;

    private Date creationDate;

    private Date orderDate;

    @Size(max = 100)
    private String trackingNumber;

    @OneToMany(mappedBy="order")
    private List<Product> products;


    public Order() {}

    public Order(Long id, String storeName, Date creationDate, Date orderDate, String trackingNumber) {
        this.id = id;
        this.storeName = storeName;
        this.creationDate = creationDate;
        this.orderDate = orderDate;
        this.trackingNumber = trackingNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

}
