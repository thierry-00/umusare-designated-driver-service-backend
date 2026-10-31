package com.umusare.designateddriverservice.service;

import com.umusare.designateddriverservice.entity.CarType;
import com.umusare.designateddriverservice.entity.Driver;
import com.umusare.designateddriverservice.entity.Zone;
import com.umusare.designateddriverservice.repository.CarTypeRepository;
import com.umusare.designateddriverservice.repository.DriverRepository;
import com.umusare.designateddriverservice.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarTypeRepository carTypeRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    public String save(Driver driver) {
        driverRepository.save(driver);
        return "Driver saved successfully";
    }

    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    public Optional<Driver> getById(UUID id) {
        return driverRepository.findById(id);
    }

    public String update(UUID id, Driver incoming) {
        Optional<Driver> match = driverRepository.findById(id);
        if (match.isEmpty()) {
            return "The driver with that id does not exist";
        }
        Driver driver = match.get();
        driver.setFirstName(incoming.getFirstName());
        driver.setLastName(incoming.getLastName());
        driver.setLicenseNumber(incoming.getLicenseNumber());
        driver.setZone(incoming.getZone());
        driver.setCarTypes(incoming.getCarTypes());
        driverRepository.save(driver);
        return "Driver updated successfully";
    }

    public String delete(UUID id) {
        if (!driverRepository.existsById(id)) {
            return "The driver with that id does not exist";
        }
        driverRepository.deleteById(id);
        return "Driver deleted successfully";
    }

    public List<Driver> getByCarType(String name) {
        return driverRepository.findByCarTypeIgnoreCase(name);
    }

    public String addCarType(UUID driverId, UUID carTypeId) {
        Optional<Driver> driverOpt = driverRepository.findById(driverId);
        if (driverOpt.isEmpty()) {
            return "The driver with that id does not exist";
        }
        Optional<CarType> carTypeOpt = carTypeRepository.findById(carTypeId);
        if (carTypeOpt.isEmpty()) {
            return "That car type does not exist";
        }
        Driver driver = driverOpt.get();
        CarType carType = carTypeOpt.get();
        if (driver.getCarTypes().contains(carType)) {
            return "The driver already has that car type";
        }
        driver.getCarTypes().add(carType);
        driverRepository.save(driver);
        return "Car type added to the driver";
    }

    public String changeZone(UUID driverId, int zoneNumber) {
        Optional<Driver> driverOpt = driverRepository.findById(driverId);
        if (driverOpt.isEmpty()) {
            return "The driver with that id does not exist";
        }
        Optional<Zone> zoneOpt = zoneRepository.findByZoneNumber(zoneNumber);
        if (zoneOpt.isEmpty()) {
            return "The zone with that zoneNumber does not exist";
        }
        Zone zone = zoneOpt.get();
        if (driverRepository.existsByZoneAndIdNot(zone, driverId)) {
            return "That zone is taken by another driver";
        }
        Driver driver = driverOpt.get();
        driver.setZone(zone);
        driverRepository.save(driver);
        return "Zone changed successfully";
    }
}
