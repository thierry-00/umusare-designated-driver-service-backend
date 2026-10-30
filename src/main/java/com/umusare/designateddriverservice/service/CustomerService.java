package com.umusare.designateddriverservice.service;

import com.umusare.designateddriverservice.entity.Customer;
import com.umusare.designateddriverservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public String save(Customer customer) {
        customerRepository.save(customer);
        return "Customer saved successfully";
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getById(UUID id) {
        return customerRepository.findById(id);
    }

    public String update(UUID id, Customer body) {
        Optional<Customer> lookup = customerRepository.findById(id);
        if (lookup.isEmpty()) {
            return "The customer with that id does not exist";
        }
        Customer c = lookup.get();
        c.setFirstName(body.getFirstName());
        c.setLastName(body.getLastName());
        c.setPhone(body.getPhone());
        c.setHomeAddress(body.getHomeAddress());
        customerRepository.save(c);
        return "Customer updated successfully";
    }

    public String delete(UUID id) {
        if (!customerRepository.existsById(id)) {
            return "The customer with that id does not exist";
        }
        customerRepository.deleteById(id);
        return "Customer deleted successfully";
    }

    public List<Customer> getByLastName(String lastName) {
        return customerRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }
}
