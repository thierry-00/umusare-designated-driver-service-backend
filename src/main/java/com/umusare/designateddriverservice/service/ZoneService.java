package com.umusare.designateddriverservice.service;

import com.umusare.designateddriverservice.entity.Zone;
import com.umusare.designateddriverservice.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public String save(Zone zone) {
        zoneRepository.save(zone);
        return "Zone saved successfully";
    }

    public List<Zone> getAll() {
        return zoneRepository.findAll();
    }

    public Optional<Zone> getById(UUID id) {
        return zoneRepository.findById(id);
    }

    public String update(UUID id, Zone data) {
        Optional<Zone> result = zoneRepository.findById(id);
        if (result.isEmpty()) {
            return "The zone with that id does not exist";
        }
        Zone zone = result.get();
        zone.setZoneNumber(data.getZoneNumber());
        zone.setName(data.getName());
        zoneRepository.save(zone);
        return "Zone updated successfully";
    }

    public String delete(UUID id) {
        if (!zoneRepository.existsById(id)) {
            return "The zone with that id does not exist";
        }
        zoneRepository.deleteById(id);
        return "Zone deleted successfully";
    }
}
