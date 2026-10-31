package com.umusare.designateddriverservice.controller;

import com.umusare.designateddriverservice.entity.Payment;
import com.umusare.designateddriverservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestParam UUID rideRequestId, @RequestBody Payment payment) {
        String msg = paymentService.save(payment, rideRequestId);
        if (msg.equals("The ride request with that id does not exist")) {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
        if (msg.equals("Payments are only for DROPPED_OFF ride requests")) {
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{phone}")
    public ResponseEntity<List<Payment>> getByCustomer(@PathVariable String phone) {
        return new ResponseEntity<>(paymentService.getByCustomer(phone), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String msg = paymentService.delete(id);
        if (msg.equals("Payment deleted successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
}
