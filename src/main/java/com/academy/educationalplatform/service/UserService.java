package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.LoginResponse;
import com.academy.educationalplatform.dto.RegisterUserRequest;
import com.academy.educationalplatform.dto.UpdateUserRequest;
import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.entity.UserRole;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.mapper.UserMapper;
import com.academy.educationalplatform.mapper.UserRoleMapper;
import com.academy.educationalplatform.repository.UserRepository;
import com.academy.educationalplatform.repository.UserRoleRepository;
import com.academy.educationalplatform.repository.UserRoleRepository;
import com.academy.educationalplatform.security.JwtService;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;  //temporarily
    private final UserRoleService userRoleService;
    private final UserRoleMapper userRoleMapper;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository, UserMapper userMapper, JwtService jwtService, UserRoleService userRoleService, UserRoleMapper userRoleMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;  //temporarily
        this.userRoleService = userRoleService;
        this.userRoleMapper = userRoleMapper;
    }

    public LoginResponse register(RegisterUserRequest request) {  //allocate to AuthService

        try {

            User user = userMapper.toEntity(request);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);



            List<Role> roles = request.getRoles();

            if (roles == null || roles.isEmpty()) {
                roles = List.of(Role.USER);
            }

            for (Role role : roles) {
                userRoleRepository.save(
                        userRoleMapper.toUserRole(user.getId(), role)
                );
            }



            String accessToken = jwtService.generateToken(user, request.getRoles());
            String refreshToken = jwtService.generateRefreshToken(user, request.getRoles());

            LoginResponse response = new LoginResponse();
            response.setAccessToken(accessToken);
            response.setRefreshToken(refreshToken);

            return response;
        } catch (RuntimeException e) {
            throw e;
        }
    }
//
//    @Transactional /*not finall*/
//    public LoginResponse register(RegisterUserRequest request) {
//
//        User user = userMapper.toEntity(request);
//
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//
//        User savedUser = userRepository.save(user);
//
//        userRoleService.joinRole(
//                savedUser.getId(),
//                Role.USER
//        );
//
//        for (Role role : request.getRoles()) {
//                UserRole userRole = new UserRole();
//                userRole.setUserId(user.getId());
//                userRole.setRole(role);
//                userMapper.applyDefaults(userRole);
//
//                userRoleRepository.save(userRole);
//            }
//
//        String accessToken = jwtService.generateToken(user, request.getRoles());
//            String refreshToken = jwtService.generateRefreshToken(user, request.getRoles());
//
//            LoginResponse response = new LoginResponse();
//            response.setAccessToken(accessToken);
//            response.setRefreshToken(refreshToken);
//
//            return response;
//    }

//    public User update(UUID id, String username, String email, String phone, String password) {
//
//        try {
//
//            User user = userRepository.findById(id).orElseThrow(() ->
//                    PlatformException.of(PlatformErrorCode.USER_NOT_FOUND));
//
//            user.setUsername(username);
//            user.setEmail(email);
//            user.setPhone(phone);
//            user.setPassword(password);
//
//            return user;
//        } catch (RuntimeException e) {
//            throw e;
//        }
//    }


public User update(UUID id, UpdateUserRequest request) {
    User user = userRepository.findById(id)
            .orElseThrow(() ->
                    PlatformException.of(PlatformErrorCode.USER_NOT_FOUND));

    userMapper.updateUserFromDto(request, user);

    return userRepository.save(user);
}



    public void delById(UUID id) {

        try {
            userRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<User> getAll() {

        try {

            List<User> users = userRepository.findAll();

            return users;

        } catch (RuntimeException e) {
            throw e;
        }
    }

    public User getById(UUID id) {
        try {

            return userRepository.findById(id).orElseThrow(() ->
                    PlatformException.of(PlatformErrorCode.USER_NOT_FOUND));
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
//


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

    public List<Role> findRolesByUserId(UUID userId) {
        return userRoleRepository.findAllByUserId(userId)
                .stream()
                .map(UserRole::getRole)
                .toList();
    }
}
