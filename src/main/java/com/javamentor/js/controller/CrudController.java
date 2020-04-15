package com.javamentor.js.controller;

import com.javamentor.js.model.JsonObject;
import com.javamentor.js.model.Role;
import com.javamentor.js.model.User;
import com.javamentor.js.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class CrudController {
    @Autowired
    private UserService service;

    @GetMapping("/admin/edit/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = service.getUserById(id);
        return ResponseEntity.ok(
              user
        );
    }
    @PostMapping("/user/request")
    public ResponseEntity<User> getUser(@RequestBody User user) {
        System.out.println(user);
        User responseUser = new User();
        responseUser.setAge(20);
        responseUser.setName("Georgiy");
        responseUser.setEmail("g@m.ru");
    return new ResponseEntity<User>(responseUser, HttpStatus.OK);
    }

    @PostMapping("/user/requestById")
    public ResponseEntity<JsonObject> getUserById(@RequestBody User user) {
        Long id = user.getId();
        User userById = service.getUserById(id);
        List<Role> allRoles = service.getAllRole();
        JsonObject jsonObject = new JsonObject();
        jsonObject.setUser(userById);
        jsonObject.setAllRoles(allRoles);

        return new ResponseEntity<JsonObject>(jsonObject, HttpStatus.OK);
    }

}
