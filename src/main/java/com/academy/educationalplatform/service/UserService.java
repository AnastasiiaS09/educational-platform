package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.entity.UserRole;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.UserRepository;
import com.academy.educationalplatform.repository.UserRoleRepository;
import com.academy.educationalplatform.repository.UserRoleRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public User register(String username, String email, String phone, String rawPassword, List<Role> roles) {
        List<Role> effectiveRoles = roles == null || roles.isEmpty()
                ? List.of(Role.USER)
                : roles.stream().distinct().toList();
        return register(username, email, phone, rawPassword, effectiveRoles.toArray(Role[]::new));
    }

    public User register(String username, String email, String phone, String rawPassword, Role... roles) {

        try {
            if (userRepository.existsByEmail(email)) {
                throw PlatformException.of(PlatformErrorCode.USER_EMAIL_EXISTS, email);
            }

            if (userRepository.existsByPhone(phone)) {
                throw PlatformException.of(PlatformErrorCode.USER_PHONE_EXISTS, phone);
            }
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(passwordEncoder.encode(rawPassword));

            for (Role role : roles) {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRole(role);
            }

            return userRepository.save(user);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public User update(Long id, String username, String email, String phone, String password) {

        try {
            if (!userRepository.existsByEmail(email)) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND, email);
            }

            User user = userRepository.findById(id).orElseThrow(() -> {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            });

            user.setUsername(username);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(password);

            return user;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void delById(Long id) {

        try {
            userRepository.deleteById(id);
            System.out.println("User was deleted successfully");
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<User> getAll() {

        try {

            List<User> users = userRepository.findAll();

            System.out.println(users);
            return users;

        } catch (RuntimeException e) {
            throw e;
        }
    }

    public User getById(Long id) {
        try {

            return userRepository.findById(id).orElseThrow(() -> {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            });
        } catch (RuntimeException e) {
            throw e;
        }
    }

//    public List<Role> findRolesByUserId(Long userId) {
//
//        userRepository.findById(userId)
//                .orElseThrow(() -> PlatformException.of(PlatformErrorCode.USER_NOT_FOUND));
//
//        return usersRoleRepository.findAllByUserId(userId)
//                .stream()
//                .map(UserRole::getRole)
//                .toList();
//    }


    public User findByEmailForLogin(String email) {
        try {

            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            }
            return user;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<Role> findRolesByUserId(Long userId) {
        return userRoleRepository.findAllByUserId(userId)
                .stream()
                .map(UserRole::getRole)
                .toList();
    }
}
