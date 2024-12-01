package org.example.repository;

import org.example.entity.CarEntity;
import org.example.entity.CarModelEntity;
import org.example.entity.DealerEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class CarModelRepositoryTest {
    @Test
    public void testCreateCarEntity() throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        CarModelRepository repository = new CarModelRepository() {
            @Override
            protected Connection getConnection() {
                return connection;
            }
        };

        CarModelEntity carModelEntity = new CarModelEntity(5,"Toyota", "Camry", "Japan", "JP");

        repository.create(carModelEntity);

        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteCarEntity() throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        CarModelRepository repository = new CarModelRepository() {
            @Override
            protected Connection getConnection() {
                return connection;
            }
        };

        repository.delete(5);

        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateCarEntity() throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        CarModelRepository repository = new CarModelRepository() {
            @Override
            protected Connection getConnection() {
                return connection;
            }
        };
        CarModelEntity carModelEntity = new CarModelEntity(5,"Toyota", "Supra", "Japan", "JP");
        repository.update(carModelEntity);
        verify(preparedStatement, times(1)).executeUpdate();
    }
}
