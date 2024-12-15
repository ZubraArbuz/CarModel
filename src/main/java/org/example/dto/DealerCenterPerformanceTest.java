package org.example.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DealerCenterPerformanceTest {

    public static void main(String[] args) throws InterruptedException {
        CarModelDTO model = new CarModelDTO(1, "Toyota", "Camry", "US", "Japan");
        List<CarDTO> carList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            carList.add(new CarDTO(i, model, null, CarDTO.CarStatus.AVAILABLE, "Standard", "Red", new BigDecimal(20000)));
        }
        List<CarDTO> showroomCars = new ArrayList<>();
        DealerCenter dealerCenter = new DealerCenter(1, "Best Cars", carList, showroomCars);
        long startTimeSingle = System.nanoTime();
        processCarsSingleThread(dealerCenter);
        long endTimeSingle = System.nanoTime();
        System.out.println("Single thread processing time: " + (endTimeSingle - startTimeSingle) + " nanoseconds");
        long startTimeMulti = System.nanoTime();
        processCarsMultiThread(dealerCenter);
        long endTimeMulti = System.nanoTime();
        System.out.println("Multi-thread processing time: " + (endTimeMulti - startTimeMulti) + " nanoseconds");
    }

    private static void processCarsSingleThread(DealerCenter dealerCenter) {
        for (CarDTO car : dealerCenter.getCars()) {
            if ("Red".equals(car.getColor())) {
                dealerCenter.getShowroomCars().add(car);
            }
        }
    }

    private static void processCarsMultiThread(DealerCenter dealerCenter) throws InterruptedException {
        int numThreads = 4; // Количество потоков
        int carsPerThread = dealerCenter.getCars().size() / numThreads;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        List<Callable<Void>> tasks = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            final int startIndex = i * carsPerThread;
            final int endIndex = (i == numThreads - 1) ? dealerCenter.getCars().size() : (i + 1) * carsPerThread;
            tasks.add(() -> {
                for (int j = startIndex; j < endIndex; j++) {
                    CarDTO car = dealerCenter.getCars().get(j);
                    if ("Red".equals(car.getColor())) {
                        dealerCenter.getShowroomCars().add(car);
                    }
                }
                return null;
            });
        }
        executorService.invokeAll(tasks);
        executorService.shutdown();
    }
}
