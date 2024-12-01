package org.example.repository;

import org.example.dto.CarDTO;
import org.example.entity.CarEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarEntityRepository {

    private static final String URL = "jdbc:postgresql://localhost:5432/carmodel_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "q1w2e3";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Создание
    public void create(CarEntity carEntity) {
        String sql = "INSERT INTO Car (id, model_id, dealer_id, status, configuration, color, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carEntity.getId());
            stmt.setInt(2, carEntity.getModel().getId());
            stmt.setInt(3, carEntity.getDealer().getId());
            stmt.setString(4, carEntity.getStatus());
            stmt.setString(5, carEntity.getConfiguration());
            stmt.setString(6, carEntity.getColor());
            stmt.setBigDecimal(7, carEntity.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение всех записей
    public List<CarEntity> getAll() {
        List<CarEntity> cars = new ArrayList<>();
        String sql = "SELECT * FROM Car";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Тут нужно будет взять модели и дилеров, используя их id
                // Этот код примерный и нужно будет доработать для полноценной работы
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    // Получение записи по ID
    public CarEntity getById(int id) {
        String sql = "SELECT * FROM Car WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Конвертировать данные и вернуть объект CarEntity
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Обновление записи
    public void update(CarEntity carEntity) {
        String sql = "UPDATE Car SET model_id = ?, dealer_id = ?, status = ?, configuration = ?, color = ?, price = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carEntity.getModel().getId());
            stmt.setInt(2, carEntity.getDealer().getId());
            stmt.setString(3, carEntity.getStatus());
            stmt.setString(4, carEntity.getConfiguration());
            stmt.setString(5, carEntity.getColor());
            stmt.setBigDecimal(6, carEntity.getPrice());
            stmt.setInt(7, carEntity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление записи
    public void delete(int id) {
        String sql = "DELETE FROM Car WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
