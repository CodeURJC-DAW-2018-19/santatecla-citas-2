package com.urjc.daw.practica.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urjc.daw.practica.model.User;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
	
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private UserComponent userComponent;

    @GetMapping("/api/login")
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

    @GetMapping("/api/logout")
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


}