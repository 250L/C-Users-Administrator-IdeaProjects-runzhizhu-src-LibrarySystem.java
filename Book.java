public class Book {
    private int id;
    private String title;
    private String genre;

    public Book(int id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', genre='" + genre + "'}";
    }
}