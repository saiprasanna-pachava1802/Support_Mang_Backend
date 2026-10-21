
package com.supportmanagement.controller;

import com.supportmanagement.entity.SupportPerson;
import com.supportmanagement.service.SupportPersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support-persons")
public class SupportPersonController {

    private final SupportPersonService service;

    public SupportPersonController(SupportPersonService service) {
        this.service = service;
    }

    @GetMapping
    public List<SupportPerson> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SupportPerson getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public SupportPerson create(@RequestBody SupportPerson person) {
        return service.save(person);
    }

    @PutMapping("/{id}")
    public SupportPerson update(
            @PathVariable Long id,
            @RequestBody SupportPerson person) {
        return service.update(id, person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
