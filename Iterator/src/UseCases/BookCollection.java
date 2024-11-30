package UseCases;

import Abastractions.Collection;
import Abastractions.Iterator;

public class BookCollection implements Collection<Book> {
    private Book[] books;
    private int count = 0;

    public BookCollection(int size) {
        books = new Book[size];
    }

    public void addBook(Book book) {
        if (count < books.length) {
            books[count++] = book;
        } else {
            System.out.println("Collection is full!");
        }
    }

    @Override
    public Iterator<Book> createIterator() {
        return new BookIterator(books);
    }
}
