package com.umusare.designateddriverservice.service;

import com.umusare.designateddriverservice.entity.CarType;
import com.umusare.designateddriverservice.repository.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarTypeService {

    @Autowired
    private CarTypeRepository carTypeRepository;

    public String save(CarType carType) {
        carTypeRepository.save(carType);
        return "Car type saved successfully";
    }

    public List<CarType> getAll() {
        return carTypeRepository.findAll();
    }

    public Optional<CarType> getById(UUID id) {
        return carTypeRepository.findById(id);
    }

    public String update(UUID id, CarType updated) {
        Optional<CarType> existing = carTypeRepository.findById(id);
        if (existing.isEmpty()) {
            return "The car type with that id does not exist";
        }
        CarType ct = existing.get();
        ct.setName(updated.getName());
        carTypeRepository.save(ct);
        return "Car type updated successfully";
    }

    public String delete(UUID id) {
        if (!carTypeRepository.existsById(id)) {
            return "The car type with that id does not exist";
        }
        carTypeRepository.deleteById(id);
        return "Car type deleted successfully";
    }
}
