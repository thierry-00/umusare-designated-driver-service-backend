package com.umusare.designateddriverservice.repository;

import com.umusare.designateddriverservice.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ZoneRepository extends JpaRepository<Zone, UUID> {
}
