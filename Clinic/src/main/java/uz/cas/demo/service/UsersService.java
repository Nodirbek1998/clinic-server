package uz.cas.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.cas.demo.entity.Attachment;
import uz.cas.demo.entity.Users;
import uz.cas.demo.exception.UsernameException;
import uz.cas.demo.payload.ReqLogin;
import uz.cas.demo.payload.ReqUsers;
import uz.cas.demo.repository.AttachmentRepository;
import uz.cas.demo.repository.RoleRepository;
import uz.cas.demo.repository.RoomsRepository;
import uz.cas.demo.repository.UsersRepository;
import uz.cas.demo.security.JwtProvider;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    RoomsRepository roomsRepository;

    @Override
    public Users loadUserByUsername(String username) throws UsernameNotFoundException {
        return  usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username dis not found"));
    }

    public ResponseEntity<?> saveUser(ReqUsers reqUsers) throws IOException {
        boolean byUsername = usersRepository.existsByUsername(reqUsers.getUsername());
        if (!byUsername){
            Users users = new Users();
            users.setFirstName(reqUsers.getFirstName());
            users.setLastName(reqUsers.getLastName());
            users.setMiddleName(reqUsers.getMiddleName());
            users.setUsername(reqUsers.getUsername());
            users.setPassword(passwordEncoder.encode(reqUsers.getPassword()));
            users.setRooms(roomsRepository.findById(reqUsers.getRooms()).get());
            users.setCategory(reqUsers.getCategory());
            users.setSpeciality(reqUsers.getSpeciality());
            users.setPrice(reqUsers.getPrice());
            users.setAttachment(attachmentRepository.findById(reqUsers.getAttachmentId()).get());
            users.setRoles(roleRepository.findAll().stream().filter(role ->
                    role.getRoleName().name().equals("doctor")).collect(Collectors.toSet()));
            usersRepository.save(users);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            reqUsers.getUsername(),
                            reqUsers.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.status(HttpStatus.OK).body(jwtProvider.generateToken(authentication));

        }
        throw new UsernameException("Username already exists!");
    }

    public ResponseEntity<?> login(ReqLogin reqLogin){
        boolean existsByUsername = usersRepository.existsByUsername(reqLogin.getUsername());
        if (existsByUsername){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            reqLogin.getUsername(),
                            reqLogin.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            Users users = loadUserByUsername(reqLogin.getUsername());
            Map<String, Object> token = new HashMap<>();
            token.put("token",jwtProvider.generateToken(authentication));
            token.put("attachmentId",users.getAttachment().getId());
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        throw new UsernameException("Username not found!");
    }

    public ResponseEntity<?> getAllDoctors(){
        List<Users> all = usersRepository.findAll();
        return ResponseEntity.ok(all);
    }
    public ResponseEntity<?> getDoctor(Integer id) {
        Optional<Users> byId = usersRepository.findById(id);
        return ResponseEntity.ok(byId.get());
    }

}
