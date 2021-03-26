package uz.cas.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.cas.demo.service.UsersService;

@Controller
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UsersController {


    @Autowired
    private UsersService usersService;

    @GetMapping
    public HttpEntity<?> getAllDoctors(){

        return ResponseEntity.ok( usersService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getDoctor(@PathVariable Integer id){
        ResponseEntity<?> doctor = usersService.getDoctor(id);
        return ResponseEntity.ok(doctor);
    }
}
