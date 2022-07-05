package com.example.minitesthouse.service;



import com.example.minitesthouse.model.Role;

public interface RoleService {
    Iterable<Role> findAll();


    void save(Role role);

    Role findByName(String name);
}
