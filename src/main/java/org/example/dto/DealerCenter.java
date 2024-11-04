package org.example.dto;

import java.util.List;

public class DealerCenter {
    private Integer id;
    private String name;
    private List<CarDTO> cars;

    public DealerCenter(Integer id, String name, List<CarDTO> cars) {
        this.id = id;
        this.name = name;
        this.cars = cars;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public List<CarDTO> getCars() { return cars; }

    public void setCars(List<CarDTO> cars) { this.cars = cars; }

    @Override
    public String toString() {
        return "DealerCenter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cars=" + cars +
                '}';
    }
}

