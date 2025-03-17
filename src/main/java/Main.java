import repository.AuthorRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException {
        Connection connection = Config.getConnection();
        AuthorRepository authorRepository = new AuthorRepository();
        //authorRepository.insertAuthor(connection,"Mark","Tvain");
        authorRepository.DeleteAuthor(connection,1L);


    }
}
