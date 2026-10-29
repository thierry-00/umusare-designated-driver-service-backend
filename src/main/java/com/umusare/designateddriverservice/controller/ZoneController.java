package com.umusare.designateddriverservice.controller;

import com.umusare.designateddriverservice.entity.Zone;
import com.umusare.designateddriverservice.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Zone zone) {
        return new ResponseEntity<>(zoneService.save(zone), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Zone>> getAll() {
        return new ResponseEntity<>(zoneService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<Zone> z = zoneService.getById(id);
        if (z.isEmpty()) {
            return new ResponseEntity<>("The zone with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(z.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Zone zone) {
        String msg = zoneService.update(id, zone);
        if (msg.equals("Zone updated successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = zoneService.delete(id);
        if (msg.equals("Zone deleted successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
}
