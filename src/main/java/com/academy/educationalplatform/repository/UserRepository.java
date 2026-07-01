package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User saveUser(User user);

    public boolean existsById(Long id);

    public boolean existsByEmail(String email);

    public boolean existsByPhone(String phone);

    public User findUserById(Long id);

    public User findUserByEmail(String email);

    public User findByPhone(String phone);

    public void deleteInUserById(Long id);

}
