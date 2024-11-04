package org.example.service.impl;

import org.example.dto.CarModelDTO;
import org.example.service.CarModelService;

import java.util.*;
import java.util.stream.Collectors;

public class CarService implements CarModelService {

    private final CarModelService carModelService = new FileSystemCarModelService();

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
