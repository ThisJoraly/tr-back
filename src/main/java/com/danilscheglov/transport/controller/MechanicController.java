package com.danilscheglov.transport.controller;

import com.danilscheglov.transport.model.Mechanic;
import com.danilscheglov.transport.service.MechanicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mechanics")
@RequiredArgsConstructor
public class MechanicController {
    private final MechanicService mechanicService;

    @GetMapping
    public List<Mechanic> getAllMechanics() {
        return mechanicService.getAllMechanics();
    }

    @GetMapping("/{id}")
    public Mechanic getMechanicById(@PathVariable Long id) {
        return mechanicService.getMechanicById(id).orElse(null);
    }

    @PostMapping
    public Mechanic createMechanic(@RequestBody Mechanic mechanic) {
        return mechanicService.saveMechanic(mechanic);
    }

    @PutMapping("/{id}")
    public Mechanic updateMechanic(@PathVariable Long id, @RequestBody Mechanic mechanic) {
        mechanic.setMechanicId(id);
        return mechanicService.saveMechanic(mechanic);
    }

    @DeleteMapping("/{id}")
    public void deleteMechanic(@PathVariable Long id) {
        mechanicService.deleteMechanic(id);
    }
}
