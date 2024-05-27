package com.dev.backend_challenge.repository;

import com.dev.backend_challenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByCpf(String cpf);
}
