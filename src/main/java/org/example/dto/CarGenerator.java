package org.example.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarGenerator {

    private static final Random random = new Random();

    private static CarDTO generateRandomCar(int id, List<CarModelDTO> carModels, DealerCenter dealerCenter) {
        CarModelDTO model = carModels.get(random.nextInt(carModels.size()));  // Случайная модель
        String[] configurations = {"Standard", "Luxury", "Premium"};
        String[] colors = {"Red", "Blue", "Black", "White", "Silver"};
        CarDTO.CarStatus[] statuses = CarDTO.CarStatus.values();

        String configuration = configurations[random.nextInt(configurations.length)];
        String color = colors[random.nextInt(colors.length)];
        CarDTO.CarStatus status = statuses[random.nextInt(statuses.length)];
        BigDecimal price = BigDecimal.valueOf(random.nextInt(50_000) + 10_000);  // Цена от 10,000 до 60,000

        return new CarDTO(id, model, dealerCenter, status, configuration, color, price);
    }

    public static List<CarDTO> generateCars(int numberOfCars, List<CarModelDTO> carModels, DealerCenter dealerCenter) {
        List<CarDTO> cars = new ArrayList<>(numberOfCars);
        for (int i = 1; i <= numberOfCars; i++) {
            cars.add(generateRandomCar(i, carModels, dealerCenter));
        }
        return cars;
    }

    public static void main(String[] args) {
        List<CarModelDTO> carModels = new ArrayList<>();
        carModels.add(new CarModelDTO(1, "Toyota", "Camry", "US", "Japan"));
        carModels.add(new CarModelDTO(2, "Honda", "Civic", "JP", "Japan"));
        carModels.add(new CarModelDTO(3, "BMW", "X5", "DE", "Germany"));
        List<CarDTO> cars = generateCars(10_000, carModels, null);
        DealerCenter dealerCenter = new DealerCenter(1, "Best Cars", cars, new ArrayList<>());
        System.out.println("Generated " + dealerCenter.getCars().size() + " cars.");
    }
}
