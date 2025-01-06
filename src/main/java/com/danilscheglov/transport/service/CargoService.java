package com.danilscheglov.transport.service;

import com.danilscheglov.transport.model.Cargo;
import com.danilscheglov.transport.model.dto.CargoRequest;
import com.danilscheglov.transport.model.dto.CargoResponse;
import com.danilscheglov.transport.model.Order;
import com.danilscheglov.transport.repository.CargoRepository;
import com.danilscheglov.transport.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CargoService {
    private final CargoRepository cargoRepository;
    private final OrderRepository orderRepository;

    public List<CargoResponse> getAllCargos() {
        return cargoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<CargoResponse> getCargoById(Long id) {
        return cargoRepository.findById(id).map(this::toResponse);
    }

    public CargoResponse saveCargo(CargoRequest cargoRequest) {
        Order order = orderRepository.findById(cargoRequest.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Cargo cargo = toEntity(cargoRequest);
        cargo.setOrder(order);
        Cargo savedCargo = cargoRepository.save(cargo);
        return toResponse(savedCargo);
    }

    public CargoResponse updateCargo(Long id, CargoRequest cargoRequest) {
        Cargo existingCargo = cargoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cargo not found"));
        Order order = orderRepository.findById(cargoRequest.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        existingCargo.setCargoType(cargoRequest.getCargoType());
        existingCargo.setCargoWeight(cargoRequest.getCargoWeight());
        existingCargo.setCargoVolume(cargoRequest.getCargoVolume());
        existingCargo.setOrder(order);

        Cargo updatedCargo = cargoRepository.save(existingCargo);
        return toResponse(updatedCargo);
    }

    public void deleteCargo(Long id) {
        cargoRepository.deleteById(id);
    }

    private Cargo toEntity(CargoRequest cargoRequest) {
        Cargo cargo = new Cargo();
        cargo.setCargoType(cargoRequest.getCargoType());
        cargo.setCargoWeight(cargoRequest.getCargoWeight());
        cargo.setCargoVolume(cargoRequest.getCargoVolume());
        return cargo;
    }

    private CargoResponse toResponse(Cargo cargo) {
        return new CargoResponse(
                cargo.getCargoId(),
                cargo.getCargoType(),
                cargo.getCargoWeight(),
                cargo.getCargoVolume(),
                cargo.getOrder().getOrderId()
        );
    }
}
