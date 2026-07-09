package com.academy.educationalplatform.security;

import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;

import java.util.Optional;
import java.util.UUID;

public final class SecurityUtils {
    private SecurityUtils() {
    }

    public static Optional<SecurityUser> optionalCurrentUser() {
        var authentication = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SecurityUser securityUser) {
            return Optional.of(securityUser);
        }
        return Optional.empty();
    }

    public static SecurityUser currentUser() {
        return optionalCurrentUser()
                .orElseThrow(() -> PlatformException.of(PlatformErrorCode.INVALID_CREDENTIALS));
    }

    public static UUID currentUserId() {
        return currentUser().getId();
    }

    public static boolean hasRole(Role role) {
        return optionalCurrentUser()
                .map(user -> user.getRoles().contains(role))
                .orElse(false);
    }

    public static boolean isAdmin() {
        return hasRole(Role.ADMIN);
    }
//
}
