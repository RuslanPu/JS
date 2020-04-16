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

    @PostMapping("/admin/updateUser")
    public String updateUser(@RequestBody User user) {
        System.out.println(user.getRoles());
        String[] roles = new String[user.getRoles().size()];
        for (int i = 0 ; i < user.getRoles().size(); i++) {
            roles[i] = user.getRoles().get(i).getName();
        }
       service.edit(user, roles);
        return "good";
    }

    @PostMapping("/user/checkEmail")
    public ResponseEntity<JsonObject> checkEmail(@RequestBody User user) {
//        Long id = user.getId();
        String email = user.getEmail();
//        User userById = service.getUserById(id);
        boolean unicEmail;
        if (!service.unicEmail(email)) {
            unicEmail = false;
        } else { unicEmail = true; }

        JsonObject jsonObject = new JsonObject();
        jsonObject.setUnicEmail(unicEmail);

        return new ResponseEntity<JsonObject>(jsonObject, HttpStatus.OK);
    }



}
