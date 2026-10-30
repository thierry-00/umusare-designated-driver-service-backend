package com.umusare.designateddriverservice.repository;

import com.umusare.designateddriverservice.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CarTypeRepository extends JpaRepository<CarType, UUID> {

    @Query("SELECT c FROM CarType c WHERE c.drivers IS EMPTY")
    List<CarType> findUnusedCarTypes();
}
