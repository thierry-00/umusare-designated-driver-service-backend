package com.umusare.designateddriverservice.repository;

import com.umusare.designateddriverservice.entity.Driver;
import com.umusare.designateddriverservice.entity.RideRequest;
import com.umusare.designateddriverservice.entity.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface RideRequestRepository extends JpaRepository<RideRequest, UUID> {

    List<RideRequest> findByStatusOrderByRideDateAsc(RideStatus status);

    List<RideRequest> findByRideDateBetweenOrderByRideDateAsc(LocalDate start, LocalDate end);

    boolean existsByDriverAndRideDateAndStatusNot(Driver driver, LocalDate rideDate, RideStatus status);

    @Query("SELECT r.status, COUNT(r) FROM RideRequest r GROUP BY r.status")
    List<Object[]> countByStatus();

    @Modifying
    @Query("UPDATE RideRequest r SET r.status = com.umusare.designateddriverservice.entity.RideStatus.CANCELLED WHERE r.driver.id = :driverId AND r.rideDate = :date AND r.status <> com.umusare.designateddriverservice.entity.RideStatus.DROPPED_OFF")
    int cancelDay(@Param("driverId") UUID driverId, @Param("date") LocalDate date);
}
