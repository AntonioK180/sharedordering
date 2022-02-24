package serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serverapp.models.authentication.ERole;
import serverapp.models.authentication.Role;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long>  {
    Optional<Role> findByName(ERole name);
}
