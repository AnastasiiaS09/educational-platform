package com.academy.educationalplatform.security;

import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.exceptions.PlatformErrorCode;
import com.academy.educationalplatform.exceptions.PlatformException;


import java.util.Optional;

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

    public static Long currentUserId() {
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

    public static void assertOwnerOrAdmin(Long userId) {
        if (!isAdmin() && !currentUserId().equals(userId)) {
            throw PlatformException.of(PlatformErrorCode.ACCESS_DENIED);
        }
    }
}
