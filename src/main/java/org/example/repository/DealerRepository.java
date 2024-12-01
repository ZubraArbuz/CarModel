package org.example.repository;

import org.example.dto.DealerDTO;
import org.example.entity.DealerEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealerRepository {

    private static final String URL = "jdbc:postgresql://localhost:5432/carmodel_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "q1w2e3";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Создание
    public void create(DealerEntity dealerEntity) {
        String sql = "INSERT INTO Dealer (id, name) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dealerEntity.getId());
            stmt.setString(2, dealerEntity.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение всех записей
    public List<DealerEntity> getAll() {
        List<DealerEntity> dealers = new ArrayList<>();
        String sql = "SELECT * FROM Dealer";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                dealers.add(new DealerEntity(rs.getInt("id"), rs.getString("name"), null)); // Здесь нужны машины, если нужно
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealers;
    }

    // Получение записи по ID
    public DealerEntity getById(int id) {
        String sql = "SELECT * FROM Dealer WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new DealerEntity(rs.getInt("id"), rs.getString("name"), null); // Здесь нужно будет получить машины
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Обновление записи
    public void update(DealerEntity dealerEntity) {
        String sql = "UPDATE Dealer SET name = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dealerEntity.getName());
            stmt.setInt(2, dealerEntity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление записи
    public void delete(int id) {
        String sql = "DELETE FROM Dealer WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
