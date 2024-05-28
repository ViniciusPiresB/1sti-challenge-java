package com.dev.backend_challenge.repository;

import com.dev.backend_challenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    User findByCpf(String cpf);

    @Query("SELECT u FROM User u WHERE u.cpf = :cpf")
    UserDetails findUserDetailsByCpf(@Param("cpf") String cpf);
}
