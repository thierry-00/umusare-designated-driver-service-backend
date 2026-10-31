package com.umusare.designateddriverservice.service;

import com.umusare.designateddriverservice.entity.Payment;
import com.umusare.designateddriverservice.entity.RideRequest;
import com.umusare.designateddriverservice.entity.RideStatus;
import com.umusare.designateddriverservice.repository.PaymentRepository;
import com.umusare.designateddriverservice.repository.RideRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RideRequestRepository rideRequestRepository;

    public String save(Payment payment, UUID rideRequestId) {
        Optional<RideRequest> rrOpt = rideRequestRepository.findById(rideRequestId);
        if (rrOpt.isEmpty()) {
            return "The ride request with that id does not exist";
        }
        RideRequest rr = rrOpt.get();
        if (rr.getStatus() != RideStatus.DROPPED_OFF) {
            return "Payments are only for DROPPED_OFF ride requests";
        }
        payment.setPaidDate(LocalDate.now());
        payment.setRideRequest(rr);
        paymentRepository.save(payment);
        return "Payment saved successfully";
    }

    public List<Payment> getByCustomer(String phone) {
        return paymentRepository.findByCustomerPhone(phone);
    }

    public String delete(UUID id) {
        if (!paymentRepository.existsById(id)) {
            return "The payment with that id does not exist";
        }
        paymentRepository.deleteById(id);
        return "Payment deleted successfully";
    }
}
