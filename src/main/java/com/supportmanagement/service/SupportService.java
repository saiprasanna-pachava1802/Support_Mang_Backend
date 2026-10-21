
package com.supportmanagement.service;

import com.supportmanagement.entity.SupportAssignment;
import com.supportmanagement.repository.SupportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportService {

    private final SupportRepository supportRepository;

    public SupportService(SupportRepository supportRepository) {
        this.supportRepository = supportRepository;
    }

    public SupportAssignment saveAssignment(
            SupportAssignment assignment) {
        return supportRepository.save(assignment);
    }

    public List<SupportAssignment> getAllAssignments() {
        return supportRepository.findAll();
    }

    public Optional<SupportAssignment> getAssignmentById(Long id) {
        return supportRepository.findById(id);
    }

    public List<SupportAssignment> getAssignmentsByStatus(
            String status) {
        return supportRepository.findByStatus(status);
    }

    public List<SupportAssignment> getAssignmentsByCustomerId(
            Long customerId) {
        return supportRepository.findByCustomerId(customerId);
    }

    public List<SupportAssignment> getAssignmentsBySupportPersonId(
            Long supportPersonId) {
        return supportRepository.findBySupportPersonId(supportPersonId);
    }

    public void deleteAssignment(Long id) {
        supportRepository.deleteById(id);
    }
}
