package uz.cas.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.cas.demo.entity.Queue;
import uz.cas.demo.entity.Users;
import uz.cas.demo.repository.QueueRepository;
import uz.cas.demo.repository.UsersRepository;
import uz.cas.demo.security.JwtFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DoctorTableService {
    @Autowired
    private QueueRepository queueRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private JwtFilter jwtFilter;

    public ResponseEntity<?> getAll(){
        Users users = jwtFilter.getGetUsers();
        Optional<Users> byId = usersRepository.findById(users.getId());
        List<Queue> all = queueRepository.findAllByUser(byId.get());
        Map<String, Object> map = new HashMap<>();
        map.put("user", users);
        map.put("tables", all);
        return ResponseEntity.ok(map);
    }

    public ResponseEntity<?> edit(Integer id){
        Queue byId = queueRepository.findById((long) id).get();
        byId.setUser(byId.getUser());
        byId.setPatient(byId.getPatient());
        byId.setName(byId.getName());
        if (!byId.isAccepted()){
            byId.setAccepted(!byId.isAccepted());
        }
        queueRepository.save(byId);
        return ResponseEntity.ok("Qabul qilindi");
    }
}
