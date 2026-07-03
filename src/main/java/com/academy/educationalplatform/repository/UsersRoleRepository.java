package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.entity.UsersRole;

import java.util.List;

public interface UsersRoleRepository {
    UsersRole save(UsersRole usersRole);

    List<UsersRole> findAll();

    UsersRole findByUserId(Long id);

    void deleteRole(String role);
}
