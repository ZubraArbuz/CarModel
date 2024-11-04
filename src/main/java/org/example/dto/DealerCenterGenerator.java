package org.example.dto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DealerCenterGenerator {

    public static DealerCenter generateDealerCenter(int centerId, String centerName, List<CarModelDTO> models) {
        Random random = new Random();
        List<CarDTO> cars = new ArrayList<>();

        for (int i = 0; i < 10; i++) { // создаем 10 автомобилей
            CarModelDTO model = models.get(random.nextInt(models.size()));
            CarDTO.CarStatus status = CarDTO.CarStatus.values()[random.nextInt(CarDTO.CarStatus.values().length)];
            String configuration = "Config" + random.nextInt(5);
            String color = random.nextBoolean() ? "Red" : "Blue";
            BigDecimal price = BigDecimal.valueOf(20000 + random.nextInt(10000));

            CarDTO car = new CarDTO(i + 1, model, null, status, configuration, color, price);
            cars.add(car);
        }

        DealerCenter dealerCenter = new DealerCenter(centerId, centerName, cars);
        for (CarDTO car : cars) {
            car.setDealerCenter(dealerCenter);
        }

        return dealerCenter;
    }
}
