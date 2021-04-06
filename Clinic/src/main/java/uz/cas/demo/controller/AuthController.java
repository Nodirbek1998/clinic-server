package uz.cas.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.cas.demo.payload.ReqLogin;
import uz.cas.demo.payload.ReqUsers;
import uz.cas.demo.service.UsersService;

import java.io.IOException;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UsersService usersService;

    @PostMapping("register")
    public HttpEntity<?> register(@RequestBody ReqUsers reqUsers) throws IOException {
        ResponseEntity<?> responseEntity = usersService.saveUser(reqUsers);
        return ResponseEntity.ok(responseEntity);
    }

    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody ReqLogin reqLogin){
        ResponseEntity<?> login = usersService.login(reqLogin);
        return ResponseEntity.ok(login);
    }
}
