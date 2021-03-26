package uz.cas.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.cas.demo.entity.Rooms;
import uz.cas.demo.exception.RoomsException;
import uz.cas.demo.payload.ApiResponse;
import uz.cas.demo.payload.ReqRoom;
import uz.cas.demo.repository.RoomsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomsRepository roomsRepository;

    public ResponseEntity<?> getAllRooms(){
        List<Rooms> all = roomsRepository.findAll();
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<?> addRoom(ReqRoom reqRoom){
        boolean exists = roomsRepository.existsByFloorAndNumber(reqRoom.getFloor(), reqRoom.getNumber());
        if (!exists){
            Rooms rooms = new Rooms();
            rooms.setFloor(reqRoom.getFloor());
            rooms.setNumber(reqRoom.getNumber());
            roomsRepository.save(rooms);
            return ResponseEntity.ok(new ApiResponse("Room saved", true));
        }
        throw new RoomsException("This room already saved");
    }

    public ResponseEntity<?> editRoom(ReqRoom reqRoom, Integer id){
        boolean idNot = roomsRepository.existsByFloorAndNumberAndIdNot(reqRoom.getFloor(), reqRoom.getNumber(), id);
        if (!idNot){
            Optional<Rooms> byId = roomsRepository.findById(id);
            if (byId.isPresent()){
                Rooms rooms = byId.get();
                rooms.setNumber(reqRoom.getNumber());
                rooms.setFloor(reqRoom.getFloor());
                roomsRepository.save(rooms);
                return ResponseEntity.ok(new ApiResponse("Room edited", true));
            }
        }
        return ResponseEntity.ok("This room do not found");
    }

    public ResponseEntity<?> deleteRoom(Integer id){
        roomsRepository.deleteById(id);
        return ResponseEntity.ok("This room deleted");
    }
}
