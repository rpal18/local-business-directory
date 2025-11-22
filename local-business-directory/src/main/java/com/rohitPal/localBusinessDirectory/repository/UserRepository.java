package com.rohitPal.localBusinessDirectory.repository;

import com.rohitPal.localBusinessDirectory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}