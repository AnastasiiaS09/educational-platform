package com.academy.educationalplatform.config;
import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.service.CourseService;
import com.academy.educationalplatform.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DemoDataLoader implements CommandLineRunner {

    private final UserService userService;
    private final CourseService courseService;

    public DemoDataLoader(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @Override
    public void run(String... args) {
        if (!userService.findAll().isEmpty()) {
            return;
        }

        userService.register("Admin", "admin@store.local", "admin123", Role.ADMIN, Role.USER);
        userService.register("Student", "student@store.local", "student123", Role.USER);
//
//        courseService.create("KB-001", "Mechanical Keyboard", "RGB switches", new BigDecimal("4500"), 20);
//        productService.create("MS-042", "Wireless Mouse", "Ergonomic", new BigDecimal("1200"), 50);
//        productService.create("HD-100", "USB Headset", "Noise cancelling", new BigDecimal("2800"), 15);
    }
}
