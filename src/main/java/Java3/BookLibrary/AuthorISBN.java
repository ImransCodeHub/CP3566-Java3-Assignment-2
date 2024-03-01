package Java3.BookLibrary;

public class AuthorISBN {
    private String isbn;
    private int authorID;

    public AuthorISBN(int authorID, String isbn) {
        this.authorID = authorID;
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAuthorID() {
        return authorID;
    }
}
