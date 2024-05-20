package com.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authservice.entity.Registration;

@Repository
public interface UserRepository extends JpaRepository<Registration, String> {
	Optional<Registration> findByUsername(String username);

	Optional<Registration> findByEmail(String email);
}
