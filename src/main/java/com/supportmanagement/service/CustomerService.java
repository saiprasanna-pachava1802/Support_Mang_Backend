
package com.supportmanagement.service;

import com.supportmanagement.entity.Customer;
import com.supportmanagement.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public Customer getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found: " + id));
    }

    public Customer save(Customer customer) {
        customer.setId(null);
        return repository.save(customer);
    }

    public Customer update(Long id, Customer updated) {
        Customer customer = getById(id);

        customer.setName(updated.getName());
        customer.setPhone(updated.getPhone());
        customer.setEmail(updated.getEmail());
        customer.setAddress(updated.getAddress());

        return repository.save(customer);
    }

    public void delete(Long id) {
        Customer customer = getById(id);
        repository.delete(customer);
    }
}
