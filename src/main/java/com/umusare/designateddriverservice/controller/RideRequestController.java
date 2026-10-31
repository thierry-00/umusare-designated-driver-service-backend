package com.umusare.designateddriverservice.controller;

import com.umusare.designateddriverservice.entity.RideRequest;
import com.umusare.designateddriverservice.entity.RideStatus;
import com.umusare.designateddriverservice.service.RideRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ride-requests")
public class RideRequestController {

    @Autowired
    private RideRequestService rideRequestService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody RideRequest rideRequest) {
        String msg = rideRequestService.save(rideRequest);
        if (msg.equals("Ride request date cannot be in the past")) {
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        if (msg.equals("Driver is already dispatched on that date")) {
            return new ResponseEntity<>(msg, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
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
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        String msg = rideRequestService.delete(id);
        if (msg.equals("Ride request deleted successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        if (msg.equals("That ride request has payments, delete them first")) {
            return new ResponseEntity<>(msg, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<RideRequest>> getByStatus(@RequestParam RideStatus status) {
        return new ResponseEntity<>(rideRequestService.getByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<List<RideRequest>> getBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(rideRequestService.getBetween(startDate, endDate), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Object> updateStatus(@PathVariable UUID id, @RequestParam RideStatus status) {
        String msg = rideRequestService.updateStatus(id, status);
        if (msg.equals("The ride request with that id does not exist")) {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
        if (msg.startsWith("Cannot change status")) {
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
