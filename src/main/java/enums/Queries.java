package enums;

public enum Queries {
    CREATE_AUTHOR_TABLE("""
                CREATE TABLE IF NOT EXISTS Author (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(255)  NOT NULL,
                    last_name VARCHAR(255) NOT NULL
                );
            """),

    INSERT_AUTHOR("INSERT INTO Author (name, last_name) VALUES (?, ?);"),

    FIND_ALL_AUTHORS("SELECT * FROM Author;"),

    FIND_Author_BY_ID("SELECT * FROM Author  where Author.id=?;"),

    UPDATE_AUTHOR("""
            UPDATE Author SET name = ?, last_name = ? WHERE id = ?;
            """),

    DELETE_AUTHOR("""
                DELETE FROM Author WHERE id = ?;
            """),

    CREATE_BOOK_TABLE("""
                CREATE TABLE IF NOT EXISTS Book (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(255)  NOT NULL,
                    year int NOT NULL,
                    author_id BIGINT NOT NULL,
                    FOREIGN KEY (author_id) REFERENCES Author(id) ON DELETE CASCADE
                );
            """),

    INSERT_BOOK("""
                INSERT INTO Book (name, year, author_id) 
                SELECT ?, ?, ?
                WHERE NOT EXISTS (
                    SELECT 1 FROM Book WHERE name = ? AND year = ? AND author_id = ?
                );
            """),

    FIND_ALL_BOOKS("SELECT Book.*, Author.name as author_name ,Author.last_name as author_last_name From Book b inner Join Author a on a.id=b.author_id;"),

    FIND_Book_BY_ID("SELECT * FROM Book  where Book.id=?;"),

    UPDATE_BOOK("""
                UPDATE Book SET name = ?,year=?, author_id = ? WHERE id = ?;
            """),

    DELETE_BOOK("""
                DELETE FROM Book WHERE id = ?;
            """);

    private final String query;

    Queries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

