package org.example.entity;

public class CarModelEntity {
    private int id;
    private String brand;
    private String modelName;
    private String countryOrigin;
    private String countryCode;

    public CarModelEntity(int id, String brand, String modelName, String countryOrigin, String countryCode) {
        this.id = id;
        this.brand = brand;
        this.modelName = modelName;
        this.countryOrigin = countryOrigin;
        this.countryCode = countryCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "CarModelEntity{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", modelName='" + modelName + '\'' +
                ", countryOrigin='" + countryOrigin + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
