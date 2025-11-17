public class Book {
    private static int nextId = 1;
    private int id;
    private String title;
    private String author;
    private boolean available;
    private int borrowedBy; // userId who borrowed, 0 if none

    public Book(String title, String author) {
        this.id = nextId++;
        this.title = title;
        this.author = author;
        this.available = true;
        this.borrowedBy = 0;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public int getBorrowedBy() { return borrowedBy; }

    public void borrow(int userId) {
        this.available = false;
        this.borrowedBy = userId;
    }

    public void returned() {
        this.available = true;
        this.borrowedBy = 0;
    }

    @Override
    public String toString() {
        String status = available ? "Available" : ("Borrowed by user " + borrowedBy);
        return "[" + id + "] " + title + " - " + author + " (" + status + ")";
    }
}
