package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    UserRepository users;


    @Test
    public void test_saveUSer(){
        User dummy = dummyUser();
        users.save(dummy);

        Assertions.assertThat(users.findAll()).extracting(User::getName).contains(dummy.getName());

    }

    @Test
    public void test_findByName(){
        User dummy = dummyUser();
        users.save(dummy);

        Assertions.assertThat(users.findByName(dummy.getName())).extracting(User::getName).isEqualTo(dummy.getName());
    }

    @Test
    public void test_checkCorrectPass_true(){

        User dummy = dummyUser();
        String pass = dummy.getPassword();
        dummy.setPassword(encoder.encode(dummy.getPassword()));
        users.save(dummy);

        Assertions.assertThat(this.encoder.matches(pass,users.findByName(dummy.getName()).getPassword()));

    }
    private User dummyUser(){
        User user = new User("test","test");
        return user;
    }

}
