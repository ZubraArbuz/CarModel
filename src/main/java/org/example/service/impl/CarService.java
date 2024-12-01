package org.example.service.impl;

import org.example.repository.CarEntityRepository;
import org.example.entity.*;
import org.example.dto.CarModelDTO;
import org.example.service.CarModelService;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CarService implements CarModelService {

    private CarEntityRepository carEntityRepository;

    private final CarModelService carModelService = new FileSystemCarModelService();

    public CarService(CarEntityRepository carEntityRepository) {
        this.carEntityRepository = carEntityRepository;
    }

    public void createCar() {
        try {
            CarModelEntity carModelEntity = new CarModelEntity(3, "Toyota", "Camry", "Japan", "JP");
            DealerEntity dealerEntity = new DealerEntity(2, "Dealer2", new ArrayList<>());
            CarEntity carEntity = new CarEntity(10, carModelEntity, dealerEntity, "old", "basic", "red", BigDecimal.valueOf(25000));
            carEntityRepository.create(carEntity);
            System.out.println("Car entity created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error creating car entity: " + e.getMessage());
        }
    }

    public void deleteCar() {
        try {

            carEntityRepository.delete(9);
            System.out.println("Car entity created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error creating car entity: " + e.getMessage());
        }
    }

    @Override
    public List<CarModelDTO> load() {
        return carModelService.load();
    }

    @Override
    public List<CarModelDTO> getAllCarDTOs(String brand) {
        return carModelService.getAllCarDTOs(brand);
    }

    @Override
    public Optional<CarModelDTO> findCarById(CarModelDTO car) {
        return carModelService.findCarById(car);
    }

    @Override
    public Map<String, Integer> getCarModelGroupByModel(String brand) {
        return carModelService.getCarModelGroupByModel(brand);
    }
    // метод возвращающий список уникальных марок автомобилей
    public List<String> getUniqueBrands() {
        return load().stream()
                .map(CarModelDTO::getBrand)
                .collect(Collectors.toSet())
                .stream()
                .collect(Collectors.toList());
    }
    // метод поиска по марке автомобиля
    public List<CarModelDTO> findModelsByBrand(String brand) {
        return load().stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }
    //группировка по марке автомобиля
    public Map<String, Integer> groupByBrand() {
        return load().stream()
                .collect(Collectors.groupingBy(
                        CarModelDTO::getBrand,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ));
    }
}
