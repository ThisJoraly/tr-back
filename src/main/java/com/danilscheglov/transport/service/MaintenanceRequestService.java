package com.danilscheglov.transport.service;

import com.danilscheglov.transport.model.MaintenanceRequest;
import com.danilscheglov.transport.repository.MaintenanceRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaintenanceRequestService {
    private final MaintenanceRequestRepository maintenanceRequestRepository;

    public List<MaintenanceRequest> getAllMaintenanceRequests() {
        return maintenanceRequestRepository.findAll();
    }

    public Optional<MaintenanceRequest> getMaintenanceRequestById(Long id) {
        return maintenanceRequestRepository.findById(id);
    }

    public MaintenanceRequest saveMaintenanceRequest(MaintenanceRequest maintenanceRequest) {
        return maintenanceRequestRepository.save(maintenanceRequest);
    }

    public void deleteMaintenanceRequest(Long id) {
        maintenanceRequestRepository.deleteById(id);
    }
}
