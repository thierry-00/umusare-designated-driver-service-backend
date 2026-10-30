package com.umusare.designateddriverservice.service;

import com.umusare.designateddriverservice.entity.RideRequest;
import com.umusare.designateddriverservice.entity.RideStatus;
import com.umusare.designateddriverservice.repository.RideRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RideRequestService {

    @Autowired
    private RideRequestRepository rideRequestRepository;

    public String save(RideRequest rideRequest) {
        rideRequestRepository.save(rideRequest);
        return "Ride request saved successfully";
    }

    public List<RideRequest> getAll() {
        return rideRequestRepository.findAll();
    }

    public Optional<RideRequest> getById(UUID id) {
        return rideRequestRepository.findById(id);
    }

    public String update(UUID id, RideRequest body) {
        Optional<RideRequest> record = rideRequestRepository.findById(id);
        if (record.isEmpty()) {
            return "The ride request with that id does not exist";
        }
        RideRequest rr = record.get();
        rr.setRideDate(body.getRideDate());
        rr.setPickupLocation(body.getPickupLocation());
        rr.setCarPlate(body.getCarPlate());
        rr.setCustomer(body.getCustomer());
        rr.setDriver(body.getDriver());
        rideRequestRepository.save(rr);
        return "Ride request updated successfully";
    }

    public String delete(UUID id) {
        if (!rideRequestRepository.existsById(id)) {
            return "The ride request with that id does not exist";
        }
        rideRequestRepository.deleteById(id);
        return "Ride request deleted successfully";
    }

    public List<RideRequest> getByStatus(RideStatus status) {
        return rideRequestRepository.findByStatusOrderByRideDateAsc(status);
    }

    public List<RideRequest> getBetween(LocalDate start, LocalDate end) {
        return rideRequestRepository.findByRideDateBetweenOrderByRideDateAsc(start, end);
    }
}
