package org.example.entity;

import java.util.List;

public class DealerEntity {
    private int id;
    private String name;
    private List<CarEntity> cars;

    public DealerEntity(int id, String name, List<CarEntity> cars) {
        this.id = id;
        this.name = name;
        this.cars = cars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CarEntity> getCars() {
        return cars;
    }

    public void setCars(List<CarEntity> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "DealerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cars=" + cars +
                '}';
    }
}
