package com.academy.educationalplatform.repository;

import com.academy.educationalplatform.entity.InviteCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface InviteCodeRepository extends JpaRepository<InviteCode, UUID> {

    boolean existsByCode(String code);

    InviteCode findByCode(String code);

    @Query("""
    FROM InviteCode ic WHERE ic.code = :code AND ic.status = "AVAILABLE"
""")
    Object isAvailable(String code);
}
