
package com.supportmanagement.repository;

import com.supportmanagement.entity.SupportAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportRepository
        extends JpaRepository<SupportAssignment, Long> {

    List<SupportAssignment> findByStatus(String status);

    List<SupportAssignment> findByCustomerId(Long customerId);

    List<SupportAssignment> findBySupportPersonId(Long supportPersonId);
}
