package org.example;

import org.example.dto.CarModelDTO;
import org.example.dto.PerformanceTester;
import org.example.service.CarModelService;
import org.example.service.impl.CarService;
import org.example.service.impl.FileSystemCarModelService;

import java.util.List;
import java.util.Optional;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CarModelService carModelService;
        CarService carService = new CarService();
        carModelService = new FileSystemCarModelService();

        var list = carModelService.load();

        System.out.println(list);

        List<CarModelDTO> toyotaCars = carModelService.getAllCarDTOs("Toyota");
        for (CarModelDTO car : toyotaCars) {
            System.out.println(car);
        }


        CarModelDTO searchCar = new CarModelDTO(2, "Honda", "Accord", "JP", "Japan");
        Optional<CarModelDTO> foundCar = carModelService.findCarById(searchCar);
        if (foundCar.isPresent()) {
            System.out.println("Найденный автомобиль: " + foundCar.get());
        } else {
            System.out.println("Автомобиль не найден.");
        }

        Map<String, Integer> toyotaModels = carModelService.getCarModelGroupByModel("Toyota");
        toyotaModels.forEach((model, count) ->
                System.out.println("Модель: " + model + ", Количество: " + count)
        );

        PerformanceTester.testMethodPerformance(() -> carService.getUniqueBrands(), "getUniqueBrands");
        PerformanceTester.testMethodPerformance(() -> carService.findModelsByBrand("Toyota"), "findModelsByBrand");
        PerformanceTester.testMethodPerformance(() -> carService.groupByBrand(), "groupByBrand");
    }
}