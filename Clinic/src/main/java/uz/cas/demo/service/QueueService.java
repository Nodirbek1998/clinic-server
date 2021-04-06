package uz.cas.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.cas.demo.entity.Queue;
import uz.cas.demo.entity.Users;
import uz.cas.demo.repository.QueueRepository;
import uz.cas.demo.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QueueService {
    @Autowired
    private QueueRepository queueRepository;
    @Autowired
    private UsersRepository usersRepository;

    public ResponseEntity<?> getAll(Integer id){
        Optional<Users> byId = usersRepository.findById(id);
        List<Queue> all = queueRepository.findAllByUser(byId.get());
        return ResponseEntity.ok(all);
    }
    public ResponseEntity<?> getAllQueue(String category){
        List<Queue> all = queueRepository.findAll();
        List<Queue> queues = new ArrayList<>();
        for (Queue queue : all) {
            if (queue.getUser().getCategory().name().charAt(0) == category.charAt(0)){
                queues.add(queue);
            }
        }
        return ResponseEntity.ok(queues);
    }
    
    public ResponseEntity<?> paid(Long id){
        Optional<Queue> byId = queueRepository.findById(id);
        Queue queue = byId.get();
        queue.setStatus(true);
        queueRepository.save(queue);
        return ResponseEntity.ok(true);
    }
    public void deleteQueue(Long id){
        queueRepository.deleteById(id);
    }
    public void addQueue(Long id){
        Optional<Queue> byId = queueRepository.findById(id);
        Queue queue = byId.get();
        queue.setStatus(true);
        queueRepository.save(queue);
    }
}
