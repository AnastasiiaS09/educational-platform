package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.UserRole;
import com.academy.educationalplatform.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRole joinRole(Long userId, Role role) {

        try {
            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUserId(userId);

            return userRoleRepository.save(userRole);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<UserRole> findUserRoles(Long userId) {
        return userRoleRepository.findAllByUserId(userId);
    }


    public void deleteUserRole(Long id) {
        userRoleRepository.deleteById(id);
    }


}
