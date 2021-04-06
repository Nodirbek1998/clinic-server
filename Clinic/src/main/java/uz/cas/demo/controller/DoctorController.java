package uz.cas.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.cas.demo.entity.Queue;
import uz.cas.demo.service.DoctorTableService;
import uz.cas.demo.service.QueueService;

@Controller
@RequestMapping("/api")
@CrossOrigin("*")
public class DoctorController {

    @Autowired
    private DoctorTableService doctorTableService;
    @Autowired
    private QueueService queueService;

    @GetMapping("doctor")
    public HttpEntity<?> getAll(){
        ResponseEntity<?> all = doctorTableService.getAll();
        return ResponseEntity.ok(all);
    }
    @PostMapping("queue")
    public HttpEntity<?> getAllQueue(@RequestBody String id){
        ResponseEntity<?> allQueue = queueService.getAllQueue(id);
        return ResponseEntity.ok(allQueue);
    }

    @GetMapping("doctor/{id}")
    public HttpEntity<?> editQueue(@PathVariable Integer id){
        ResponseEntity<?> edit = doctorTableService.edit(id);
        return ResponseEntity.ok(edit);
    }
}
