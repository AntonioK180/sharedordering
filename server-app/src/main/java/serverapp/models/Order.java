package serverapp.models;

import javax.persistence.*;
import java.sql.Date;
import javax.validation.constraints.Size;

@Entity
@Table(name = "shared_orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private String storeName;

    private int peopleParticipating = 1;

    private Date creationDate;

    private Date orderDate;

    private double priceSum;

    @Size(max = 100)
    private String trackingNumber;

}
