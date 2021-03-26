package uz.cas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cas.demo.entity.Role;
import uz.cas.demo.entity.Users;
import uz.cas.demo.entity.enums.RoleName;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users>  findByUsername(String username);

    boolean existsByUsername(String username);

   // List<Users> findAllByRoles(Set<Role> roles);
}
