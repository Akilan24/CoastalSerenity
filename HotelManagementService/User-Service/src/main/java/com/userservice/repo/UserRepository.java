package com.userservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.entity.Registration;

@Repository
public interface UserRepository extends JpaRepository<Registration, String> {
	Optional<Registration> findByUsername(String username);

	Optional<Registration> findByMobile(String mobile);

	Optional<Registration> findByEmail(String email);

}
