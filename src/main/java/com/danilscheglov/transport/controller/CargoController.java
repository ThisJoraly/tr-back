package com.danilscheglov.transport.controller;

import com.danilscheglov.transport.model.dto.CargoRequest;
import com.danilscheglov.transport.model.dto.CargoResponse;
import com.danilscheglov.transport.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cargos")
@RequiredArgsConstructor
public class CargoController {
    private final CargoService cargoService;

    @GetMapping
    public List<CargoResponse> getAllCargos() {
        return cargoService.getAllCargos();
    }

    @GetMapping("/{id}")
    public CargoResponse getCargoById(@PathVariable Long id) {
        return cargoService.getCargoById(id).orElse(null);
    }

    @PostMapping
    public CargoResponse createCargo(@RequestBody CargoRequest cargoRequest) {
        return cargoService.saveCargo(cargoRequest);
    }

    @PutMapping("/{id}")
    public CargoResponse updateCargo(@PathVariable Long id, @RequestBody CargoRequest cargoRequest) {
        return cargoService.updateCargo(id, cargoRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCargo(@PathVariable Long id) {
        cargoService.deleteCargo(id);
    }
}
