package repository;

import enums.Queries;
import exception.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    public void createBookTable(Connection connection) throws SQLException {
        String query = Queries.CREATE_BOOK_TABLE.getQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        System.out.println("Book table created succesfully");
    }

    public void insertBook(Connection connection, String name, int year, int author_id) throws SQLException {
        String query = Queries.INSERT_BOOK.getQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, year);
        preparedStatement.setInt(4, author_id);
        preparedStatement.executeUpdate();
    }

    public List<BookResponce> findBooks(Connection connection) throws SQLException {
        String query = Queries.FIND_ALL_BOOKS.getQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<BookResponce> books = new ArrayList<>();
        while (resultSet.next()) {
            BookResponce bookResponce = new BookResponce();
            bookResponce.setId(resultSet.getLong("id"));
            bookResponce.setName(resultSet.getString("name"));
            bookResponce.setYear(resultSet.getInt("year"));
            AuthorResponce authorResponce = new AuthorResponce();
            authorResponce.setId(resultSet.getLong("author_id"));
            authorResponce.setName(resultSet.getString("author_name"));
            authorResponce.setLast_name(resultSet.getString("author_last_name"));
            bookResponce.setAuthor(authorResponce);
        }
        return books;
    }

    public void updateBook(Connection connection) throws SQLException {
        try {
            String query = Queries.FIND_Book_BY_ID.getQuery();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (NotFoundException e) {
            throw new RuntimeException("Cant find the id ", e);
        }
        String query = Queries.UPDATE_BOOK.getQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    public ResultSet findByID(Connection connection, Long id) throws SQLException {
        try {
            String query = Queries.FIND_Book_BY_ID.getQuery();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            preparedStatement.setLong(1, id);
            return resultSet;

        } catch (NotFoundException e) {
            throw new RuntimeException("Cant find the id ", e);
        }
    }

    public void DeleteBook(Connection connection, Long id) throws SQLException {
        try {
            String query = Queries.DELETE_BOOK.getQuery();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
        } catch (NotFoundException e) {
            throw new RuntimeException("Cant find the id ", e);
        }


    }
}
