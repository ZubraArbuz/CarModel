package org.example.service.impl;

import org.junit.jupiter.engine.config.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.dto.CarModelDTO;
import org.example.service.CarModelService;

import java.util.List;
import java.util.Map;
import java.util.Optional;


class FileSystemCarModelServiceTest {

    private CarModelService carModelService;

    @BeforeEach
    void setUp() {
        carModelService = new FileSystemCarModelService();
    }

    @Test
    void testLoad() {
        List<CarModelDTO> cars = carModelService.load();
        assertEquals(5, cars.size()); // Ожидаем 5 автомобилей в списке

        CarModelDTO firstCar = cars.get(0);
        assertEquals("Toyota", firstCar.getBrand());
        assertEquals("Corolla", firstCar.getModelName());
    }

    @Test
    void testGetAllCarDTOs_Toyota() {
        List<CarModelDTO> toyotaCars = carModelService.getAllCarDTOs("Toyota");
        assertEquals(3, toyotaCars.size()); // Ожидаем 3 автомобиля марки Toyota

        for (CarModelDTO car : toyotaCars) {
            assertEquals("Toyota", car.getBrand());
        }
    }

    @Test
    void testGetAllCarDTOs_Honda() {
        List<CarModelDTO> hondaCars = carModelService.getAllCarDTOs("Honda");
        assertEquals(2, hondaCars.size()); // Ожидаем 2 автомобиля марки Honda

        for (CarModelDTO car : hondaCars) {
            assertEquals("Honda", car.getBrand());
        }
    }

    @Test
    void testFindCarById() {
        CarModelDTO carToFind = new CarModelDTO(2, "Honda", "Accord", "JP", "Japan");
        Optional<CarModelDTO> foundCar = carModelService.findCarById(carToFind);
        assertTrue(foundCar.isPresent());
        assertEquals("Honda", foundCar.get().getBrand());
        assertEquals("Accord", foundCar.get().getModelName());
    }
}
