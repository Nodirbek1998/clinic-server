package uz.cas.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.cas.demo.payload.ReqRoom;
import uz.cas.demo.service.RoomService;

@Controller
@RequestMapping("/api/room")
public class RoomsController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public HttpEntity<?> addRoom(@RequestBody ReqRoom reqRoom){
        ResponseEntity<?> responseEntity = roomService.addRoom(reqRoom);
        return ResponseEntity.ok(responseEntity);
    }

    @GetMapping
    public HttpEntity<?> getAllRooms(){
        ResponseEntity<?> allRooms = roomService.getAllRooms();
        return ResponseEntity.ok(allRooms);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editRoom(@RequestBody ReqRoom reqRoom, @PathVariable Integer id){
        ResponseEntity<?> responseEntity = roomService.editRoom(reqRoom, id);
        return ResponseEntity.ok(responseEntity);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRoom(@PathVariable Integer id){
        ResponseEntity<?> responseEntity = roomService.deleteRoom(id);
        return ResponseEntity.ok(responseEntity);
    }
}
