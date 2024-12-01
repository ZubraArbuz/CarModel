package org.example;

import org.example.dto.CarModelDTO;
import org.example.dto.PerformanceTester;
import org.example.repository.CarEntityRepository;
import org.example.service.CarModelService;
import org.example.service.impl.CarService;
import org.example.service.impl.FileSystemCarModelService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Map;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/carmodel_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "q1w2e3";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection to PostgreSQL established successfully!");
        } catch (SQLException e) {
            System.out.println("Error while connecting to PostgreSQL: " + e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {

        CarEntityRepository carEntityRepository = new CarEntityRepository();

        Connection connection = connect();
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.out.println("Error while closing connection: " + e.getMessage());
            }
        }
        CarModelService carModelService;
        CarService carService = new CarService(carEntityRepository);
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

//        carService.createCar();
        carService.deleteCar();
    }
}