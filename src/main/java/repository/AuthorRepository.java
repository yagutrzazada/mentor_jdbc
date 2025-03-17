package repository;

import enums.Queries;
import exception.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRepository {
    public void createAuthorTable(Connection connection) throws SQLException {
        String query = Queries.CREATE_AUTHOR_TABLE.getQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        System.out.println("Created successfully");
    }

    public void insertAuthor(Connection connection, String name, String lastName) throws SQLException {
        String query = Queries.INSERT_AUTHOR.getQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.execute();
    }

    public ResultSet getAllAuthors(Connection connection) throws SQLException {
        String query = Queries.FIND_ALL_AUTHORS.getQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public void updateAuthor(Connection connection) throws SQLException {
        try {
            String query = Queries.FIND_Author_BY_ID.getQuery();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (NotFoundException e) {
            throw new RuntimeException("Cant find the id ", e);
        }
        String query = Queries.UPDATE_AUTHOR.getQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    public void DeleteAuthor(Connection connection, Long id) throws SQLException {
        try {
            String query = Queries.DELETE_AUTHOR.getQuery();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
        } catch (NotFoundException e) {
            throw new RuntimeException("Cant find the id ", e);
        }
    }
}
