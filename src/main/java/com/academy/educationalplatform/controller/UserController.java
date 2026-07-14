package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.ApiMapper;
import com.academy.educationalplatform.dto.RegisterUserRequest;
import com.academy.educationalplatform.dto.UpdateUserRequest;
import com.academy.educationalplatform.dto.UserResponse;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
//
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody RegisterUserRequest request) {
        User user = userService.register(request);
        return toResponse(user);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> list() {
        return userService.getAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable UUID id) {
        return toResponse(userService.getById(id));
    }


//    @PutMapping("/{id}")
//    public UserResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateUserRequest request) {
//        var user = userService.update(id, request.getUsername(), request.getEmail(), request.getPhone(), request.getPassword());
//        return toResponse(user);
//    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable UUID id,
                               @Valid @RequestBody UpdateUserRequest request) {
        var user = userService.update(id, request);
        return toResponse(user);
    }

//    @PatchMapping("/{id}")
//    public User update(@PathVariable UUID id,
//                       @RequestBody Map<String, Object> updates) {
//        return userService.update(id, updates);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable UUID id) {
        userService.delById(id);
    }

    private UserResponse toResponse(User user) {
        return ApiMapper.toUserResponse(
                user
        );
    }
}
