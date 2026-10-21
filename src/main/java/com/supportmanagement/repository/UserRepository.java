
package com.supportmanagement.repository;

import com.supportmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserType(String userType);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
