package com.umusare.designateddriverservice.repository;

import com.umusare.designateddriverservice.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {
}
