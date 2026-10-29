package com.umusare.designateddriverservice.repository;

import com.umusare.designateddriverservice.entity.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RideRequestRepository extends JpaRepository<RideRequest, UUID> {
}
