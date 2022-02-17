package serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serverapp.models.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);
}
