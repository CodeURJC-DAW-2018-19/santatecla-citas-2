package com.urjc.daw.practica.api;

import com.urjc.daw.practica.repository.UserRepository;
import com.urjc.daw.practica.security.UserComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.urjc.daw.practica.model.User;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@RestController
public class LoginController {
	
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private UserComponent userComponent;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/api/login")
    public ResponseEntity<User>login(){
        if(!userComponent.isLoggedUser()){
            log.info("usuario no registrado");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }else{
            User loggedUser=userComponent.getLoggedUser();
            log.info("Entra");
            return new ResponseEntity<>(loggedUser,HttpStatus.OK);
        }
    }

    @RequestMapping("/api/logout")
    public ResponseEntity<Boolean>logout(HttpSession session){
        if(!userComponent.isLoggedUser()){
            log.info("Usuario no registrado");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }else{
            session.invalidate();
            log.info("sesion finalizada");
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
    }

    @PostMapping("/api/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody User user) {
        System.out.printf(user.toString());
        user.setRoles(user.getRoles());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

}