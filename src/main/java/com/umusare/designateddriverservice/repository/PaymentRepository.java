package com.umusare.designateddriverservice.repository;

import com.umusare.designateddriverservice.entity.Payment;
import com.umusare.designateddriverservice.entity.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    @Query("SELECT p FROM Payment p WHERE p.rideRequest.customer.phone = :phone ORDER BY p.paidDate DESC")
    List<Payment> findByCustomerPhone(@Param("phone") String phone);

    boolean existsByRideRequest(RideRequest rideRequest);
}
