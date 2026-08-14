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
import com.academy.educationalplatform.repository.*;
import com.academy.educationalplatform.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.UUID;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserCourseRepository userCourseRepository;
    private final UserModuleRepository userModuleRepository;
    private final UserLessonRepository userLessonRepository;
    private final UserMapper userMapper;
    private final UserRoleService userRoleService;
    private final UserRoleMapper userRoleMapper;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, UserCourseRepository userCourseRepository, UserModuleRepository userModuleRepository, UserLessonRepository userLessonRepository, UserMapper userMapper, UserRoleService userRoleService, UserRoleMapper userRoleMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userCourseRepository = userCourseRepository;
        this.userModuleRepository = userModuleRepository;
        this.userLessonRepository = userLessonRepository;
        this.userMapper = userMapper;
        this.userRoleService = userRoleService;
        this.userRoleMapper = userRoleMapper;
    }


//
//    @Transactional /*not final*/
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


public User update(UpdateUserRequest request) {

    UUID id = SecurityUtils.currentUserId();

    User user = userRepository.findById(id)
            .orElseThrow(() ->
                    PlatformException.of(PlatformErrorCode.USER_NOT_FOUND));

    userMapper.updateUserFromDto(request, user);

    return userRepository.save(user);
}



    @Transactional
    public void delById(UUID id) {

        try {
            if(!userRepository.existsById(id)) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            }  if (!SecurityUtils.isAdmin()) {
                SecurityUtils.assertOwner(id);
            }
            userRoleRepository.deleteAllByUserId(id);
            userLessonRepository.deleteAllByUserId(id);
            userModuleRepository.deleteAllByUserId(id);
            userCourseRepository.deleteAllByUserId(id);
            userRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<User> getAll() {

        try {
            if (!SecurityUtils.isAdmin()) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            }

            List<User> users = userRepository.findAll();

            return users;

        } catch (RuntimeException e) {
            throw e;
        }
    }

    public User getById(UUID id) {
        try {
            if (!SecurityUtils.isAdmin()) {
                throw PlatformException.of(PlatformErrorCode.USER_NOT_FOUND);
            }
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
