package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.ApiMapper;
import com.academy.educationalplatform.dto.RegisterUserRequest;
import com.academy.educationalplatform.dto.UpdateUserRequest;
import com.academy.educationalplatform.dto.UserResponse;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.security.SecurityUtils;
import com.academy.educationalplatform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        var user = userService.register(
                request.getUsername(),
                request.getEmail(),
                request.getPhone(),
                request.getPassword(),
                request.getRoles()
        );
        return toResponse(user);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> list() {
        return userService.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{email}")
    public UserResponse get(@PathVariable String email) {
        return toResponse(userService.getByEmail(email));
    }

    @PutMapping("/{email}")
    public UserResponse update(@PathVariable String email, @Valid @RequestBody UpdateUserRequest request) {
        SecurityUtils.assertOwnerOrAdmin(email);
        var user = userService.updateUsername(request.getUsername(), request.getEmail());
        return toResponse(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    private UserResponse toResponse(User user) {
        return ApiMapper.toUserResponse(
                user,
                userService.findRolesByUserId(user.getId())
        );
    }
}
