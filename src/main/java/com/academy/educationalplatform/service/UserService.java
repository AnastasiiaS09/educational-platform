package com.academy.educationalplatform.service;

import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.entity.UsersRole;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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
                UsersRole usersRole = new UsersRole();
                usersRole.setUserId(user.getId());
                usersRole.setRole(role);
            }

            return userRepository.save(user);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public User updateUsername(String username, String email) {

        try {
            if (!userRepository.existsByEmail(email)) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND, email);
            }

            User user = userRepository.findByEmail(email);

            user.setUsername(username);

            return user;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void delByEmail(String email) {

        try {
            if (!userRepository.existsByEmail(email)) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND, email);
            }

            userRepository.deleteByEmail(email);
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
        } finally {
        }
    }

    public User getByEmail(String email) {
        try {

            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            }

            return user;
        } catch (RuntimeException e) {
            throw e;
        } finally {
        }
    }
}
