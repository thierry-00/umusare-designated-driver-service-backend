package com.umusare.designateddriverservice.controller;

import com.umusare.designateddriverservice.entity.RideRequest;
import com.umusare.designateddriverservice.service.RideRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ride-requests")
public class RideRequestController {

    @Autowired
    private RideRequestService rideRequestService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody RideRequest rideRequest) {
        return new ResponseEntity<>(rideRequestService.save(rideRequest), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RideRequest>> getAll() {
        return new ResponseEntity<>(rideRequestService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<RideRequest> rr = rideRequestService.getById(id);
        if (rr.isEmpty()) {
            return new ResponseEntity<>("The ride request with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rr.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody RideRequest rideRequest) {
        String msg = rideRequestService.update(id, rideRequest);
        if (msg.equals("Ride request updated successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = rideRequestService.delete(id);
        if (msg.equals("Ride request deleted successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
}
