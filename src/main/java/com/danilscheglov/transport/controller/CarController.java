package com.danilscheglov.transport.controller;

import com.danilscheglov.transport.model.dto.CarRequest;
import com.danilscheglov.transport.model.dto.CarResponse;
import com.danilscheglov.transport.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public List<CarResponse> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public CarResponse getCarById(@PathVariable Long id) {
        return carService.getCarById(id).orElse(null);
    }

    @PostMapping
    public CarResponse createCar(@RequestBody CarRequest carRequest) {
        return carService.saveCar(carRequest);
    }

    @PutMapping("/{id}")
    public CarResponse updateCar(@PathVariable Long id, @RequestBody CarRequest carRequest) {
        return carService.updateCar(id, carRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
