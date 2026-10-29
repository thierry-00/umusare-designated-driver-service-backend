package com.umusare.designateddriverservice.controller;

import com.umusare.designateddriverservice.entity.Driver;
import com.umusare.designateddriverservice.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Driver driver) {
        return new ResponseEntity<>(driverService.save(driver), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Driver>> getAll() {
        return new ResponseEntity<>(driverService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<Driver> d = driverService.getById(id);
        if (d.isEmpty()) {
            return new ResponseEntity<>("The driver with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(d.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Driver driver) {
        String msg = driverService.update(id, driver);
        if (msg.equals("Driver updated successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = driverService.delete(id);
        if (msg.equals("Driver deleted successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
}
