import Abastractions.Iterator;
import UseCases.Book;
import UseCases.BookCollection;

public class App {
    public static void main(String[] args) throws Exception {
        BookCollection collection = new BookCollection(5);
        collection.addBook(new Book("Book 1"));
        collection.addBook(new Book("Book 2"));
        collection.addBook(new Book("Book 3"));

        Iterator<Book> iterator = collection.createIterator();

        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println("Book: " + book.getTitle());
        }
    }
}
