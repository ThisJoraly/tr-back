package com.danilscheglov.transport.controller;

import com.danilscheglov.transport.model.MaintenanceRequest;
import com.danilscheglov.transport.service.MaintenanceRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance-requests")
@RequiredArgsConstructor
public class MaintenanceRequestController {
    private final MaintenanceRequestService maintenanceRequestService;

    @GetMapping
    public List<MaintenanceRequest> getAllMaintenanceRequests() {
        return maintenanceRequestService.getAllMaintenanceRequests();
    }

    @GetMapping("/{id}")
    public MaintenanceRequest getMaintenanceRequestById(@PathVariable Long id) {
        return maintenanceRequestService.getMaintenanceRequestById(id).orElse(null);
    }

    @PostMapping
    public MaintenanceRequest createMaintenanceRequest(@RequestBody MaintenanceRequest maintenanceRequest) {
        return maintenanceRequestService.saveMaintenanceRequest(maintenanceRequest);
    }

    @PutMapping("/{id}")
    public MaintenanceRequest updateMaintenanceRequest(@PathVariable Long id, @RequestBody MaintenanceRequest maintenanceRequest) {
        maintenanceRequest.setMaintenanceRequestId(id);
        return maintenanceRequestService.saveMaintenanceRequest(maintenanceRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteMaintenanceRequest(@PathVariable Long id) {
        maintenanceRequestService.deleteMaintenanceRequest(id);
    }
}
