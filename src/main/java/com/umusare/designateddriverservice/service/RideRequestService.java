package com.umusare.designateddriverservice.service;

import com.umusare.designateddriverservice.entity.RideRequest;
import com.umusare.designateddriverservice.entity.RideStatus;
import com.umusare.designateddriverservice.repository.RideRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RideRequestService {

    @Autowired
    private RideRequestRepository rideRequestRepository;

    public String save(RideRequest rideRequest) {
        rideRequest.setStatus(RideStatus.REQUESTED);
        if (rideRequest.getRideDate().isBefore(LocalDate.now())) {
            return "Ride request date cannot be in the past";
        }
        if (rideRequest.getDriver() != null && rideRequestRepository.existsByDriverAndRideDateAndStatusNot(rideRequest.getDriver(), rideRequest.getRideDate(), RideStatus.CANCELLED)) {
            return "Driver is already dispatched on that date";
        }
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

    @Transactional
    public String updateStatus(UUID id, RideStatus newStatus) {
        Optional<RideRequest> record = rideRequestRepository.findById(id);
        if (record.isEmpty()) {
            return "The ride request with that id does not exist";
        }
        RideRequest rr = record.get();
        RideStatus current = rr.getStatus();
        boolean allowed = (current == RideStatus.REQUESTED && (newStatus == RideStatus.ASSIGNED || newStatus == RideStatus.CANCELLED))
                || (current == RideStatus.ASSIGNED && (newStatus == RideStatus.DROPPED_OFF || newStatus == RideStatus.CANCELLED));
        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }
        rr.setStatus(newStatus);
        rideRequestRepository.save(rr);
        return "Ride request status updated successfully";
    }
}
