package com.dev.backend_challenge.repository;

import com.dev.backend_challenge.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findByUserId(String userId);
}
