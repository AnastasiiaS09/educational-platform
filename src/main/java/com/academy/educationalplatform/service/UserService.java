package com.academy.educationalplatform.service;
import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.entity.UsersRole;
import com.academy.educationalplatform.exceptions.PlatformErrorCode;
import com.academy.educationalplatform.exceptions.PlatformException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    private final SessionFactory sessionFactory;
    private final PasswordEncoder passwordEncoder;

    public UserService(SessionFactory sessionFactory, PasswordEncoder passwordEncoder) {
        this.sessionFactory = sessionFactory;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String username, String email, String password, List<Role> roles) {
        List<Role> effectiveRoles = roles == null || roles.isEmpty()
                ? List.of(Role.USER)
                : roles.stream().distinct().toList();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {

            User existing = session
                    .createQuery("FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResultOptional()
                    .orElse(null);
            if (existing != null) {
                throw PlatformException.of(PlatformErrorCode.USER_EMAIL_EXISTS, email);
            }

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            session.persist(user);
            session.flush();

            for (Role role : roles) {
                UsersRole usersRole = new UsersRole();
                usersRole.setUser(user);
                usersRole.setRole(role);
                session.persist(usersRole);
            }


            tx.commit();
            return user;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}

