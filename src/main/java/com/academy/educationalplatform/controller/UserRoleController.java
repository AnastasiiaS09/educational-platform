package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.*;
import com.academy.educationalplatform.entity.UserRole;
import com.academy.educationalplatform.service.UserRoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserRoleResponse create(@Valid @RequestBody UserRoleRequest request) {
        var userRole = userRoleService.joinRole(
                request.getUserId(),
                String.valueOf(request.getRole())
        );
        return toResponse(userRole);
    }

    @PutMapping("/{id}")
    public UserRoleResponse update(@PathVariable Long id, @Valid @RequestBody UpdateUserRoleRequest request) {
        var userRole = userRoleService.update(id, String.valueOf(request.getRole()));
        return toResponse(userRole);
    }

    @GetMapping("/{userId}")
    public List<UserRoleResponse> findRolesByUserId(Long userId) {
        return userRoleService.findUserRole(userId).stream()
                .map(this::toResponse)
                .toList();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        userRoleService.deleteUserRole(id);
    }



    private UserRoleResponse toResponse(UserRole userRole) {
        return ApiMapper.toUserRoleResponse(
                userRole
        );
    }
}
