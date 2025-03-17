package repository;

public class BookResponce {
    private Long id;
    private String name;
    private int year;
    private AuthorResponce author;

    public AuthorResponce getAuthor() {
        return author;
    }

    public void setAuthor(AuthorResponce author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "BookResponce{" +
                "author=" + author +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}
