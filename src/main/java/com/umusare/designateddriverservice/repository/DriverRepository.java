package com.umusare.designateddriverservice.repository;

import com.umusare.designateddriverservice.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {

    @Query("SELECT d FROM Driver d JOIN d.carTypes c WHERE LOWER(c.name) = LOWER(:name)")
    List<Driver> findByCarTypeIgnoreCase(@Param("name") String name);
}
