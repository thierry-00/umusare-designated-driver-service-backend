package com.umusare.designateddriverservice.repository;

import com.umusare.designateddriverservice.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ZoneRepository extends JpaRepository<Zone, UUID> {

    @Query("SELECT z FROM Zone z WHERE NOT EXISTS (SELECT d FROM Driver d WHERE d.zone = z) ORDER BY z.zoneNumber ASC")
    List<Zone> findFreeZones();
}
