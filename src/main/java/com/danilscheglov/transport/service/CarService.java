package com.danilscheglov.transport.service;

import com.danilscheglov.transport.model.dto.CarRequest;
import com.danilscheglov.transport.model.dto.CarResponse;
import com.danilscheglov.transport.model.Car;
import com.danilscheglov.transport.model.Driver;
import com.danilscheglov.transport.repository.CarRepository;
import com.danilscheglov.transport.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<CarResponse> getCarById(Long id) {
        return carRepository.findById(id).map(this::toResponse);
    }

    public CarResponse saveCar(CarRequest carRequest) {
        Driver driver = driverRepository.findById(carRequest.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        Car car = toEntity(carRequest);
        car.setDriver(driver);
        Car savedCar = carRepository.save(car);
        log.info("Saving car: {}", savedCar);
        return toResponse(savedCar);
    }

    public CarResponse updateCar(Long id, CarRequest carRequest) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        Driver driver = driverRepository.findById(carRequest.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        existingCar.setCarNumber(carRequest.getCarNumber());
        existingCar.setCarModel(carRequest.getCarModel());
        existingCar.setCarBrand(carRequest.getCarBrand());
        existingCar.setCarCapacity(carRequest.getCarCapacity());
        existingCar.setCarMileage(carRequest.getCarMileage());
        existingCar.setCarCondition(carRequest.getCarCondition());
        existingCar.setDriver(driver);

        Car updatedCar = carRepository.save(existingCar);
        log.info("Updating car: {}", updatedCar);
        return toResponse(updatedCar);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    private Car toEntity(CarRequest carRequest) {
        Car car = new Car();
        car.setCarNumber(carRequest.getCarNumber());
        car.setCarModel(carRequest.getCarModel());
        car.setCarBrand(carRequest.getCarBrand());
        car.setCarCapacity(carRequest.getCarCapacity());
        car.setCarMileage(carRequest.getCarMileage());
        car.setCarCondition(carRequest.getCarCondition());
        return car;
    }

    private CarResponse toResponse(Car car) {
        return new CarResponse(
                car.getCarId(),
                car.getCarNumber(),
                car.getCarModel(),
                car.getCarBrand(),
                car.getCarCapacity(),
                car.getCarMileage(),
                car.getCarCondition(),
                car.getCarLastMaintenanceDate(),
                car.getDriver().getDriverId()
        );
    }
}
