package org.example.repository;

import org.example.dto.CarModelDTO;
import org.example.entity.CarModelEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarModelRepository {

    private static final String URL = "jdbc:postgresql://localhost:5432/carmodel_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "q1w2e3";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Создание
    public void create(CarModelEntity carModelEntity) {
        String sql = "INSERT INTO CarModel (id, brand, modelName, country_origin, country_code) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carModelEntity.getId());
            stmt.setString(2, carModelEntity.getBrand());
         stmt.setString(3, carModelEntity.getModelName());
            stmt.setString(4, carModelEntity.getCountryOrigin());
            stmt.setString(5, carModelEntity.getCountryCode());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение всех записей
    public List<CarModelEntity> getAll() {
        List<CarModelEntity> models = new ArrayList<>();
        String sql = "SELECT * FROM CarModel";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                models.add(new CarModelEntity(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("modelName"),
                        rs.getString("country_origin"),
                        rs.getString("country_code")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }

    // Получение записи по ID
    public CarModelEntity getById(int id) {
        String sql = "SELECT * FROM CarModel WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CarModelEntity(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("modelName"),
                        rs.getString("country_origin"),
                        rs.getString("country_code")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Обновление записи
    public void update(CarModelEntity carModelEntity) {
        String sql = "UPDATE CarModel SET brand = ?, modelName = ?, country_origin = ?, country_code = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carModelEntity.getBrand());
            stmt.setString(2, carModelEntity.getModelName());
            stmt.setString(3, carModelEntity.getCountryOrigin());
            stmt.setString(4, carModelEntity.getCountryCode());
            stmt.setInt(5, carModelEntity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление записи
    public void delete(int id) {
        String sql = "DELETE FROM CarModel WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
