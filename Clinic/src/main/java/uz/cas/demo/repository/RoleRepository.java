package uz.cas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cas.demo.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

   // Optional<Role> findById(Long aLong);
}
