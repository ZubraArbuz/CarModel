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

public class CarEntityRepositoryTest {

    @Test
    public void testCreateCarEntity() throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        CarEntityRepository repository = new CarEntityRepository() {
            @Override
            protected Connection getConnection() {
                return connection;
            }
        };

        CarModelEntity carModelEntity = new CarModelEntity(3, "Toyota", "Camry", "Japan", "JP");
        DealerEntity dealerEntity = new DealerEntity(2, "Dealer2", new ArrayList<>());
        CarEntity carEntity = new CarEntity(8, carModelEntity, dealerEntity, "new", "basic", "red", BigDecimal.valueOf(10000));

        repository.create(carEntity);

        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateCarEntity() throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        CarEntityRepository repository = new CarEntityRepository() {
            @Override
            protected Connection getConnection() {
                return connection;
            }
        };

        CarModelEntity carModelEntity = new CarModelEntity(3, "Toyota", "Camry", "Japan", "JP");
        DealerEntity dealerEntity = new DealerEntity(2, "Dealer2", new ArrayList<>());
        CarEntity carEntity = new CarEntity(8, carModelEntity, dealerEntity, "new", "dsadas", "red", BigDecimal.valueOf(10000));

        repository.update(carEntity);
        verify(preparedStatement, times(1)).executeUpdate();
    }
    @Test
    public void testDeleteCarEntity() throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        CarEntityRepository repository = new CarEntityRepository() {
            @Override
            protected Connection getConnection() {
                return connection;
            }
        };


        repository.delete(8);

        verify(preparedStatement, times(1)).executeUpdate();
    }

}
