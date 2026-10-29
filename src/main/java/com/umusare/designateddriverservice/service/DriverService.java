package com.umusare.designateddriverservice.service;

import com.umusare.designateddriverservice.entity.Driver;
import com.umusare.designateddriverservice.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

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
}
