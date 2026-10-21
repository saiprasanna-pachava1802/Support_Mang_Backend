
package com.supportmanagement.repository;

import com.supportmanagement.entity.UserTechnology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTechnologyRepository
        extends JpaRepository<UserTechnology, Long> {

    List<UserTechnology> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
