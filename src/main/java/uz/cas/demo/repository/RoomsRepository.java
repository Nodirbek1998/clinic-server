package uz.cas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cas.demo.entity.Rooms;

import java.util.Optional;

public interface RoomsRepository extends JpaRepository<Rooms, Integer> {

    Optional<Rooms> findById(Integer id);
    boolean existsByFloorAndNumber(String floor, String number);

    boolean existsByFloorAndNumberAndIdNot(String floor, String number, Integer id);
}
