package com.javamentor.js.dao;

import com.javamentor.js.model.Role;

import java.util.List;


public interface RoleDao {
    void add(Role role);
    Role getRoleById(Long id);
    Role getRoleByName(String name);
    List<Role> getAllRole();

}
