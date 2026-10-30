package com.umusare.designateddriverservice.controller;

import com.umusare.designateddriverservice.entity.Customer;
import com.umusare.designateddriverservice.service.CustomerService;
import com.umusare.designateddriverservice.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DriverService driverService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAll() {
        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<Customer> c = customerService.getById(id);
        if (c.isEmpty()) {
            return new ResponseEntity<>("The customer with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(c.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Customer customer) {
        String msg = customerService.update(id, customer);
        if (msg.equals("Customer updated successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = customerService.delete(id);
        if (msg.equals("Customer deleted successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/by-last-name")
    public ResponseEntity<List<Customer>> getByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(customerService.getByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/of-driver/{driverId}")
    public ResponseEntity<Object> getCustomersOfDriver(@PathVariable UUID driverId) {
        if (driverService.getById(driverId).isEmpty()) {
            return new ResponseEntity<>("The driver with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerService.getByDriver(driverId), HttpStatus.OK);
    }
}
