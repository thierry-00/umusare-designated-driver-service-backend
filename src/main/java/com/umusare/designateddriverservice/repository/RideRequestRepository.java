package com.umusare.designateddriverservice.repository;

import com.umusare.designateddriverservice.entity.RideRequest;
import com.umusare.designateddriverservice.entity.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface RideRequestRepository extends JpaRepository<RideRequest, UUID> {

    List<RideRequest> findByStatusOrderByRideDateAsc(RideStatus status);

    List<RideRequest> findByRideDateBetweenOrderByRideDateAsc(LocalDate start, LocalDate end);
}
