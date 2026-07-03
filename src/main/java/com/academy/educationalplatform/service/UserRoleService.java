package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.UserRole;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRole joinRole(Long userId, String role) {

        try {
            UserRole userRole = new UserRole();
            userRole.setRole(Role.valueOf(role));
            userRole.setUserId(userId);

            return userRoleRepository.save(userRole);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<UserRole> findUserRole(Long userId) {
        try {
            if (!userRoleRepository.existsByUserId(userId)) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            }

            return userRoleRepository.findAllByUserId(userId);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public UserRole update(Long id, String role) {
        try {
            UserRole userRole = userRoleRepository.findById(id).orElseThrow(() -> {
                throw PlatformException.of(PlatformErrorCode.SOMETHING_WHERE_WRONG);
            });
            userRole.setRole(Role.valueOf(role));

            return userRole;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void deleteUserRole(Long id) {
        userRoleRepository.deleteById(id);
    }


}
