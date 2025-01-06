package com.danilscheglov.transport.service;

import com.danilscheglov.transport.model.Mechanic;
import com.danilscheglov.transport.repository.MechanicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MechanicService {
    private final MechanicRepository mechanicRepository;

    public List<Mechanic> getAllMechanics() {
        return mechanicRepository.findAll();
    }

    public Optional<Mechanic> getMechanicById(Long id) {
        return mechanicRepository.findById(id);
    }

    public Mechanic saveMechanic(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    public void deleteMechanic(Long id) {
        mechanicRepository.deleteById(id);
    }
}
