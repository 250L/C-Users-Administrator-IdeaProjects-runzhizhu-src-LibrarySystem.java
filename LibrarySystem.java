import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LibrarySystem {

    // 按添加顺序保存所有图书
    private List<Book> bookList = new ArrayList<>();

    // 保存唯一图书类型
    private Set<String> genres = new HashSet<>();

    // 根据ID快速查找图书 O(1)
    private Map<Integer, Book> bookMap = new HashMap<>();

    // 添加图书，同时维护三个集合
    public void addBook(Book book) {
        if (book == null) return;

        bookList.add(book);
        genres.add(book.getGenre());
        bookMap.put(book.getId(), book);
    }

    // 使用 Iterator 安全删除，避免 ConcurrentModificationException
    public void removeBooksByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) return;

        Iterator<Book> iterator = bookList.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getTitle().contains(keyword)) {
                // 从 List 移除
                iterator.remove();

                // 从 Map 移除
                bookMap.remove(book.getId());
            }
        }

        // 重新更新 genres（因为删除后可能某类没有书了）
        genres.clear();
        for (Book b : bookList) {
            genres.add(b.getGenre());
        }
    }

    // 展示当前所有集合状态
    public void displayStatus() {
        System.out.println("===== Library Status =====");

        System.out.println("\n1. All Books (ArrayList):");
        for (Book book : bookList) {
            System.out.println(book);
        }

        System.out.println("\n2. Unique Genres (HashSet):");
        for (String genre : genres) {
            System.out.println("- " + genre);
        }

        System.out.println("\n3. Books by ID (HashMap):");
        for (Map.Entry<Integer, Book> entry : bookMap.entrySet()) {
            System.out.println("ID " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("==========================\n");
    }

    // 测试
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();

        library.addBook(new Book(101, "Java Programming", "Technology"));
        library.addBook(new Book(102, "Effective Java", "Technology"));
        library.addBook(new Book(103, "The Great Gatsby", "Fiction"));
        library.addBook(new Book(104, "1984", "Fiction"));

        library.displayStatus();

        // 删除包含 "Java" 的书
        System.out.println("After removing books with keyword 'Java':\n");
        library.removeBooksByKeyword("Java");

        library.displayStatus();
    }
}