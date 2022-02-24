package serverapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import serverapp.models.authentication.ERole;
import serverapp.models.authentication.Role;
import serverapp.repositories.RoleRepo;

@Component
public class DataOnStartupService {
    @Autowired
    private final RoleRepo roleRepo;

    public DataOnStartupService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        if(!roleRepo.findByName(ERole.ROLE_USER).isPresent()) {
            roleRepo.save(new Role(ERole.ROLE_USER));
        }

        if(!roleRepo.findByName(ERole.ROLE_MODERATOR).isPresent()) {
            roleRepo.save(new Role(ERole.ROLE_MODERATOR));
        }

        if(!roleRepo.findByName(ERole.ROLE_ADMIN).isPresent()) {
            roleRepo.save(new Role(ERole.ROLE_ADMIN));
        }
    }
}
