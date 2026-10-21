
package com.supportmanagement.service;

import com.supportmanagement.entity.SupportPerson;
import com.supportmanagement.repository.SupportPersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportPersonService {

    private final SupportPersonRepository repository;

    public SupportPersonService(SupportPersonRepository repository) {
        this.repository = repository;
    }

    public List<SupportPerson> getAll() {
        return repository.findAll();
    }

    public SupportPerson getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Support person not found: " + id));
    }

    public SupportPerson save(SupportPerson person) {
        person.setId(null);
        return repository.save(person);
    }

    public SupportPerson update(Long id, SupportPerson updated) {
        SupportPerson person = getById(id);

        person.setName(updated.getName());
        person.setPhone(updated.getPhone());
        person.setEmail(updated.getEmail());

        return repository.save(person);
    }

    public void delete(Long id) {
        SupportPerson person = getById(id);
        repository.delete(person);
    }
}
