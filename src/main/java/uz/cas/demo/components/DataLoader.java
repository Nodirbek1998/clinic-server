package uz.cas.demo.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.cas.demo.entity.Attachment;
import uz.cas.demo.entity.Role;
import uz.cas.demo.entity.Rooms;
import uz.cas.demo.entity.Users;
import uz.cas.demo.entity.enums.Category;
import uz.cas.demo.entity.enums.RoleName;
import uz.cas.demo.repository.RoleRepository;
import uz.cas.demo.repository.RoomsRepository;
import uz.cas.demo.repository.UsersRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoomsRepository roomsRepository;

    @Value("${spring.datasource.initialization-mode}")
    private String modeInitial;


    @Override
    public void run(String... args) throws Exception {

        if (modeInitial.equals("always")) {
            roleRepository.save(new Role(RoleName.admin));
            roleRepository.save(new Role(RoleName.doctor));
            roomsRepository.save(new Rooms("1","1"));
            HashSet<Role> roles = new HashSet<>(roleRepository.findAll());
            Rooms rooms = roomsRepository.findById(1).get();
            userRepository.save(new Users(
                    "admin",
                    "admin",
                    "admin",
                    "0",
                    "admin",
                    Category.A,
                    rooms,
                    "nodir",
                    passwordEncoder.encode("1234"),
                    roles.stream().filter(role -> role.getRoleName().name()
                            .equals("admin")).collect(Collectors.toSet())
            ));
        }

    }
}
