package com.umusare.designateddriverservice.repository;

import com.umusare.designateddriverservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT r.customer FROM RideRequest r WHERE r.driver.id = :driverId")
    List<Customer> findCustomersOfDriver(@Param("driverId") UUID driverId);
}
