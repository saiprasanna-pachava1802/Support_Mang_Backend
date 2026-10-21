
package com.supportmanagement.repository;

import com.supportmanagement.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findBySupportAssignmentId(Long supportId);
}
