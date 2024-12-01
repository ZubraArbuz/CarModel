package org.example.repository;

import org.example.entity.CarEntity;
import org.example.entity.CarModelEntity;
import org.example.entity.DealerEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DealerRepositoryTest {
    @Test
    public void testCreateDealer() throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        DealerRepository repository = new DealerRepository() {
            @Override
            protected Connection getConnection() {
                return connection;
            }
        };

        DealerEntity dealerEntity = new DealerEntity(3, "Dealer3", new ArrayList<>());

        repository.create(dealerEntity);

        verify(preparedStatement, times(1)).executeUpdate();
    }
    @Test
    public void testUpdateDealer() throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        DealerRepository repository = new DealerRepository() {
            @Override
            protected Connection getConnection() {
                return connection;
            }
        };
        DealerEntity dealerEntity = new DealerEntity(3, "Dealer2", new ArrayList<>());
        repository.update(dealerEntity);
        verify(preparedStatement, times(1)).executeUpdate();
    }
    @Test
    public void testDeleteDealer() throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        DealerRepository repository = new DealerRepository() {
            @Override
            protected Connection getConnection() {
                return connection;
            }
        };
        repository.delete(3);
        verify(preparedStatement, times(1)).executeUpdate();
    }
}
