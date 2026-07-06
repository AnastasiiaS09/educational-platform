package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.entity.UserRole;
import com.academy.educationalplatform.entity.UserRole;

import java.util.List;

public interface UsersRoleRepository {
    UserRole save(UserRole usersRole);

    List<UserRole> findAll();

    UserRole findByUserId(Long id);

    void deleteRole(String role);
}
