package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User saveUser(String userName, String email, String phone);

    public boolean existById(Long id);

    public boolean existByEmail(String email);

    public boolean existByPhone(String phone);

    public User findUserById(Long id);

    public User findUserByIdAndEmailAndPhone(Long id, String email, String phone);

    public void deleteInUserById(Long id);

}
