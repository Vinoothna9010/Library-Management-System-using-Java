import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {
    // simple constants
    private static final double DEPOSIT = 50.0;

    // storage
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static User current = null;

    public static void main(String[] args) {
        seedBooks();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to My First Library");

        while (true) {
            if (current == null) {
                System.out.println("\n1) Sign up  2) Login  3) Exit");
                System.out.print("Choose: ");
                String c = sc.nextLine().trim();
                if (c.equals("1")) signup(sc);
                else if (c.equals("2")) login(sc);
                else if (c.equals("3")) { System.out.println("Good Bye ! Have a Great day !"); break; }
                else System.out.println("Invalid");
            } else {
                userMenu(sc);
            }
        }
        sc.close();
    }

    private static void seedBooks() {
        books.add(new Book("The Alchemist", "Paulo Coelho"));
        books.add(new Book("Clean Code", "Robert C. Martin"));
        books.add(new Book("Intro to Algorithms", "Cormen"));
        books.add(new Book("Atomic Habits", "James Clear"));
        books.add(new Book("Psychology of Money", "Morgan Housel"));
        books.add(new Book("Think and Grow Rich", "Napoleon Hill"));
        books.add(new Book("Richest Man in Babylon", "George Clason"));
        books.add(new Book("Deep Work", "Cal Newport"));
        books.add(new Book("7 Habits", "Stephen Covey"));
        books.add(new Book("I Will Teach You To Be Rich", "Ramit Sethi"));
    }

    private static void signup(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        if (findUserByEmail(email) != null) {
            System.out.println("Email already used. Please login.");
            return;
        }
        System.out.print("Password: ");
        String pw = sc.nextLine().trim();
        System.out.print("Initial wallet (0 to skip): ");
        double init = 0;
        try { init = Double.parseDouble(sc.nextLine().trim()); } catch (Exception e) { init = 0; }
        User u = new User(name, email, pw, init);
        users.add(u);
        System.out.println("Created. Your user id: " + u.getId());
    }

    private static void login(Scanner sc) {
        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        User u = findUserByEmail(email);
        if (u == null) { System.out.println("No account. Sign up."); return; }
        System.out.print("Password: ");
        String pw = sc.nextLine().trim();
        if (!u.checkPassword(pw)) { System.out.println("Wrong password."); return; }
        current = u;
        System.out.println("Welcome " + current.getName() + " (id " + current.getId() + ")");
    }

    private static User findUserByEmail(String email) {
        for (User u : users) if (u.getEmail().equalsIgnoreCase(email)) return u;
        return null;
    }

    private static void userMenu(Scanner sc) {
        System.out.println("\n1) List all books  2) List available  3) Borrow  4) Return");
        System.out.println("5) My borrowed  6) Wallet/top-up  7) Logout  0) Exit");
        System.out.print("Choose: ");
        String op = sc.nextLine().trim();
        switch (op) {
            case "1": listAll(); break;
            case "2": listAvailable(); break;
            case "3": borrowBook(sc); break;
            case "4": returnBook(sc); break;
            case "5": myBorrowed(); break;
            case "6": wallet(sc); break;
            case "7": current = null; System.out.println("Logged out."); break;
            case "0": System.out.println("Bye"); System.exit(0); break;
            default: System.out.println("Invalid"); break;
        }
    }

    private static void listAll() {
        System.out.println("\nAll books:");
        for (Book b : books) System.out.println(b);
    }

    private static void listAvailable() {
        System.out.println("\nAvailable books:");
        for (Book b : books) if (b.isAvailable()) System.out.println(b);
    }

    private static Book findBookById(int id) {
        for (Book b : books) if (b.getId() == id) return b;
        return null;
    }

    private static void borrowBook(Scanner sc) {
        listAvailable();
        System.out.print("Enter book id to borrow (0 cancel): ");
        int id = readInt(sc);
        if (id == 0) return;
        Book b = findBookById(id);
        if (b == null) { System.out.println("Invalid id"); return; }
        if (!b.isAvailable()) { System.out.println("Not available"); return; }
        if (!current.deductWallet(DEPOSIT)) {
            System.out.println("Insufficient wallet. Need ₹" + String.format("%.2f", DEPOSIT));
            return;
        }
        b.borrow(current.getId());
        current.borrowBook(id);
        System.out.println("Borrowed. Deposit ₹" + DEPOSIT + " deducted.");
    }

    private static void returnBook(Scanner sc) {
        myBorrowed();
        System.out.print("Enter book id to return (0 cancel): ");
        int id = readInt(sc);
        if (id == 0) return;
        Book b = findBookById(id);
        if (b == null) { System.out.println("Invalid id"); return; }
        if (b.isAvailable() || b.getBorrowedBy() != current.getId()) {
            System.out.println("You did not borrow this book.");
            return;
        }
        b.returned();
        current.returnBook(id);
        current.addWallet(DEPOSIT);
        System.out.println("Returned. Deposit refunded ₹" + DEPOSIT);
    }

    private static void myBorrowed() {
        System.out.println("\nYour borrowed books:");
        if (current.getBorrowed().isEmpty()) System.out.println("None");
        else {
            for (int id : current.getBorrowed()) {
                Book b = findBookById(id);
                if (b != null) System.out.println(b);
            }
        }
    }

    private static void wallet(Scanner sc) {
        System.out.println("Wallet: ₹" + String.format("%.2f", current.getWallet()));
        System.out.print("Enter top-up amount (0 cancel): ");
        double a = 0;
        try { a = Double.parseDouble(sc.nextLine().trim()); } catch (Exception e) { a = 0; }
        if (a > 0) {
            current.addWallet(a);
            System.out.println("Updated wallet: ₹" + String.format("%.2f", current.getWallet()));
        } else System.out.println("Cancelled");
    }

    private static int readInt(Scanner sc) {
        try { return Integer.parseInt(sc.nextLine().trim()); }
        catch (Exception e) { return -1; }
    }
}
