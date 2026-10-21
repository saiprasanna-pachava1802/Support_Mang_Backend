
package com.supportmanagement.repository;

import com.supportmanagement.entity.SupportPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportPersonRepository
        extends JpaRepository<SupportPerson, Long> {
}
