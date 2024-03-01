package Java3.BookLibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Book class with isbn, title, editionNumber, and authorList attributes
 */
public class Book {
    private String isbn;
    private String title;
    private int editionNumber;
    private String copyright;
    private List<Author> authorList;

    /**
     * Constructor for Book
     * @param isbn Book's isbn
     * @param title Book's title
     * @param editionNumber Book's editionNumber
     * @param copyright Book's copyright
     */
    public Book(String isbn, String title, int editionNumber, String copyright) {
        this.isbn = isbn;
        this.title = title;
        this.editionNumber = editionNumber;
        this.copyright = copyright;
        this.authorList = new ArrayList<>();;
    }

    /**
     * Getters and setters for Book attributes
     */
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    /**
     * Adds an author to the book's authorList
     * @param author Author to add
     */
    public void addAuthor(Author author) {
        authorList.add(author);
    }
}
