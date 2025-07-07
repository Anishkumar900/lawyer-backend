package com.lawyer.repository;

import com.lawyer.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register,Long> {

    Register findByEmail(String email);
    boolean existsByEmail(String email);
}
