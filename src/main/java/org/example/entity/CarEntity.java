package org.example.entity;

import java.math.BigDecimal;

public class CarEntity {
    private int id;
    private CarModelEntity model;
    private DealerEntity dealer;
    private String status;
    private String configuration;
    private String color;
    private BigDecimal price;

    public CarEntity(int id, CarModelEntity model, DealerEntity dealer, String status, String configuration, String color, BigDecimal price) {
        this.id = id;
        this.model = model;
        this.dealer = dealer;
        this.status = status;
        this.configuration = configuration;
        this.color = color;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarModelEntity getModel() {
        return model;
    }

    public void setModel(CarModelEntity model) {
        this.model = model;
    }

    public DealerEntity getDealer() {
        return dealer;
    }

    public void setDealer(DealerEntity dealer) {
        this.dealer = dealer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + id +
                ", model=" + model +
                ", dealer=" + dealer +
                ", status='" + status + '\'' +
                ", configuration='" + configuration + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}
