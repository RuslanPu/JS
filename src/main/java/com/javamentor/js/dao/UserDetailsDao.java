package com.javamentor.js.dao;

import com.javamentor.js.model.User;


public interface UserDetailsDao {

    User getUserByName(String username);

}
