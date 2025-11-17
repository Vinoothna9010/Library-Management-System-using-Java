import java.util.ArrayList;

public class User {
    private static int nextId = 1001;
    private int id;
    private String name;
    private String email;
    private String password; // plain text for demo only
    private double wallet;
    private ArrayList<Integer> borrowed; // book IDs

    public User(String name, String email, String password, double initial) {
        this.id = nextId++;
        this.name = name;
        this.email = email;
        this.password = password;
        this.wallet = initial;
        this.borrowed = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public boolean checkPassword(String pw) { return password.equals(pw); }

    public double getWallet() { return wallet; }
    public void addWallet(double a) { wallet += a; }
    public boolean deductWallet(double a) {
        if (wallet >= a) {
            wallet -= a;
            return true;
        }
        return false;
    }

    public void borrowBook(int bookId) { borrowed.add(bookId); }
    public void returnBook(int bookId) { borrowed.remove(Integer.valueOf(bookId)); }
    public ArrayList<Integer> getBorrowed() { return borrowed; }

    @Override
    public String toString() {
        return "User " + id + ": " + name + " | Wallet: ₹" + String.format("%.2f", wallet)
               + " | Borrowed: " + borrowed;
    }
}
