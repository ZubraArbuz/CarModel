package org.example.service.impl;

import org.example.dto.CarModelDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    private CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarService();
    }

    @Test
    void testGetUniqueBrands() {
        List<String> uniqueBrands = carService.getUniqueBrands();

        assertEquals(2, uniqueBrands.size());
        assertTrue(uniqueBrands.contains("Toyota"));
        assertTrue(uniqueBrands.contains("Honda"));
    }

    @Test
    void testFindModelsByBrand_Toyota() {
        List<CarModelDTO> toyotaCars = carService.findModelsByBrand("Toyota");

        assertEquals(3, toyotaCars.size());

        for (CarModelDTO car : toyotaCars) {
            assertEquals("Toyota", car.getBrand());
        }
    }

    @Test
    void testFindModelsByBrand_Honda() {
        List<CarModelDTO> hondaCars = carService.findModelsByBrand("Honda");

        assertEquals(2, hondaCars.size());

        for (CarModelDTO car : hondaCars) {
            assertEquals("Honda", car.getBrand());
        }
    }

    @Test
    void testGroupByBrand() {
        Map<String, Integer> brandCount = carService.groupByBrand();

        assertEquals(2, brandCount.size());
        assertEquals(3, brandCount.get("Toyota"));
        assertEquals(2, brandCount.get("Honda"));
    }
}
