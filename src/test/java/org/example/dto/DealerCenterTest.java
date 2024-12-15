package org.example.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DealerCenterTest {
    private DealerCenter dealerCenter;

    @BeforeEach
    public void setUp() {
        CarModelDTO model1 = new CarModelDTO(1, "Toyota", "Camry", "US", "Japan");
        CarModelDTO model2 = new CarModelDTO(2, "Honda", "Civic", "JP", "Japan");
        CarModelDTO model3 = new CarModelDTO(3, "BMW", "X5", "DE", "Germany");
        List<CarDTO> cars = new ArrayList<>();
        cars.add(new CarDTO(1, model1, null, CarDTO.CarStatus.AVAILABLE, "Standard", "Red", new BigDecimal(20000)));
        cars.add(new CarDTO(2, model2, null, CarDTO.CarStatus.IN_STOCK, "Luxury", "Blue", new BigDecimal(25000)));
        cars.add(new CarDTO(3, model3, null, CarDTO.CarStatus.AVAILABLE, "Premium", "Black", new BigDecimal(40000)));
        dealerCenter = new DealerCenter(1, "Best Cars", cars, new ArrayList<>());
    }

    @Test
    public void testProcessCarsInMultipleThreads() throws InterruptedException {
        List<CarDTO> carsToProcess = dealerCenter.getCars();
        Thread thread1 = new Thread(() -> dealerCenter.processCars(carsToProcess.subList(0, 2)));
        Thread thread2 = new Thread(() -> dealerCenter.processCars(carsToProcess.subList(2, 3)));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertEquals(2, dealerCenter.getShowroomCars().size());
        assertTrue(dealerCenter.getShowroomCars().stream().anyMatch(car -> car.getModel().getModelName().equals("Camry")));
    }
}
