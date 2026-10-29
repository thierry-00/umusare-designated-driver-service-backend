package com.umusare.designateddriverservice.repository;

import com.umusare.designateddriverservice.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarTypeRepository extends JpaRepository<CarType, UUID> {
}
