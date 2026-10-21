
package com.supportmanagement.controller;

import com.supportmanagement.entity.SupportAssignment;
import com.supportmanagement.service.SupportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support")
@CrossOrigin(origins = "http://localhost:5173")
public class SupportController {

    private final SupportService service;

    public SupportController(SupportService service) {
        this.service = service;
    }

    @GetMapping
    public List<SupportAssignment> getAll() {
        return service.getAllAssignments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportAssignment> getById(
            @PathVariable Long id) {

        return service.getAssignmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SupportAssignment create(
            @RequestBody SupportAssignment assignment) {
        return service.saveAssignment(assignment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (service.getAssignmentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
