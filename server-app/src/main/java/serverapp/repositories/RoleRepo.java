package serverapp.repositories;

import serverapp.models.authentication.ERole;
import serverapp.models.authentication.Role;

import java.util.Optional;

public interface RoleRepo {
    Optional<Role> findByName(ERole name);
}
