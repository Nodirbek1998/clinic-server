package uz.cas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cas.demo.entity.Queue;
import uz.cas.demo.entity.Users;

import java.util.List;
import java.util.Optional;


public interface QueueRepository extends JpaRepository<Queue, Long> {

    List<Queue> findAllByUser(Users user);
}
