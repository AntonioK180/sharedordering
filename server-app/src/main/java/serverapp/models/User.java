package serverapp.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Size(max = 20)
    @Column(unique = true, nullable = false)
    private String username;

    @Size(max = 50)
    @Column(unique = true, nullable = false)
    private String email;

    @Size(max = 120)
    @Column(nullable = false)
    private String password;

//    @OneToMany(mappedBy = "user")
//    private List<Product> userProducts;
}
