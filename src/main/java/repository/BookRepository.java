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
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, year);
        preparedStatement.setInt(3, author_id);
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
        try{
            String query=Queries.FIND_BY_ID.getQuery();
        }catch (NotFoundException e){
            System.out.println("Cant find the id ");
        }
        String query = Queries.UPDATE_BOOK.getQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

    }
}
