package com.umusare.designateddriverservice.controller;

import com.umusare.designateddriverservice.entity.CarType;
import com.umusare.designateddriverservice.service.CarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/car-types")
public class CarTypeController {

    @Autowired
    private CarTypeService carTypeService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody CarType carType) {
        return new ResponseEntity<>(carTypeService.save(carType), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarType>> getAll() {
        return new ResponseEntity<>(carTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<CarType> ct = carTypeService.getById(id);
        if (ct.isPresent()) {
            return new ResponseEntity<>(ct.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The car type with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody CarType carType) {
        String msg = carTypeService.update(id, carType);
        if (msg.equals("Car type updated successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = carTypeService.delete(id);
        if (msg.equals("Car type deleted successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
}
