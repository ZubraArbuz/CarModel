package org.example.dto;

import java.math.BigDecimal;

public class CarDTO {
    private Integer id;
    private CarModelDTO model;
    private DealerCenter dealerCenter;
    private CarStatus status;
    private String configuration;
    private String color;
    private BigDecimal price;

    public enum CarStatus {
        AVAILABLE, IN_TRANSIT, IN_STOCK, SOLD, RESERVED
    }

    public CarDTO(Integer id, CarModelDTO model, DealerCenter dealerCenter, CarStatus status, String configuration, String color, BigDecimal price) {
        this.id = id;
        this.model = model;
        this.dealerCenter = dealerCenter;
        this.status = status;
        this.configuration = configuration;
        this.color = color;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public CarModelDTO getModel() {
        return model;
    }

    public DealerCenter getDealerCenter() {
        return dealerCenter;
    }

    public CarStatus getStatus() {
        return status;
    }

    public String getConfiguration() {
        return configuration;
    }

    public String getColor() {
        return color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDealerCenter(DealerCenter dealerCenter) {
        this.dealerCenter = dealerCenter;
    }

    public boolean isAvailableForShowroom() {
        return this.status == CarStatus.AVAILABLE;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", model=" + model +
                ", status=" + status +
                ", configuration='" + configuration + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}
