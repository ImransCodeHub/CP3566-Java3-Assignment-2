package Java3.BookLibrary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DBManager class to manage the database
 * This class is responsible for all database operations
 */
public class DBManager {

    /**
     * Retrieves all books from the database and populates the author list for each book.
     * @return A list of all books in the database. Each book will have a list of authors.
     */
    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = getBooksDBConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM titles");
            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                int editionNumber = rs.getInt("editionNumber");
                String copyright = rs.getString("copyright");
                Book book = new Book(isbn, title, editionNumber, copyright);
                books.add(book);
            }

            for (Book book : books) {
                List<Author> authors = getAuthorsForBook(book.getIsbn());
                book.setAuthorList(authors);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Retrieves all authors from the database and populates the book list for each author.
     * @return A list of all authors in the database. Each author will have a list of books.
     */
    private static List<Author> getAuthorsForBook(String isbn) {
        List<Author> authors = new ArrayList<>();
        try {
            Connection connection = getBooksDBConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT authorID FROM authorISBN WHERE isbn = ?");
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int authorID = rs.getInt("authorID");
                Author author = getAuthorByID(authorID);
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    /**
     * Retrieves an author from the database by their ID.
     * @param authorID The ID of the author to retrieve.
     * @return The author with the specified ID, or null if no author is found.
     */
    private static Author getAuthorByID(int authorID) {
        Author author = null;
        try {
            Connection connection = getBooksDBConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM authors WHERE authorID = ?");
            stmt.setInt(1, authorID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                author = new Author(authorID, firstName, lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    /**
     * Get all books from the database
     * Note: this method is dangerous if the database is large.
     * @return List of books
     * @throws SQLException
     */
//    public static List<Book> getAllBooks() throws SQLException {
//        LinkedList bookList = new LinkedList();
//
//        Connection connection = getBooksDBConnection();
//        Statement statement = connection.createStatement();
//        String sqlQuery = "SELECT * from " + DBProperties.BOOK_TABLE_NAME;
//
//        ResultSet resultSet = statement.executeQuery(sqlQuery);
//
//        while(resultSet.next()) {
//            bookList.add(
//                    new Book(
//                            resultSet.getString(DBProperties.BOOK_COL_NAME_ISBN),
//                            resultSet.getString(DBProperties.BOOK_COL_NAME_TITLE),
//                            resultSet.getInt(DBProperties.BOOK_COL_NAME_EDITION_NUMBER),
//                            resultSet.getString(DBProperties.BOOK_COL_NAME_COPYRIGHT)
//                    )
//            );
//        }
//        return bookList;
//    }

    // Operations for getting all authors with their books.
    /**
     * Retrieves all authors from the database and populates the book list for each author.
     * @return A list of all authors in the database. Each author will have a list of books.
     */
    public static List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try {
            Connection connection = getBooksDBConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM authors");
            while (rs.next()) {
                int authorID = rs.getInt("authorID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Author author = new Author(authorID, firstName, lastName);
                authors.add(author);
            }

            for (Author author : authors) {
                List<Book> books = getBooksForAuthor(author.getAuthorID());
                author.setBookList(books);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    /**
     * Retrieves all books associated with an author from the database.
     * @param authorID The ID of the author to retrieve books for.
     * @return A list of books associated with the author.
     */
    private static List<Book> getBooksForAuthor(int authorID) {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = getBooksDBConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT isbn FROM authorISBN WHERE authorID = ?");
            stmt.setInt(1, authorID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String isbn = rs.getString("isbn");
                Book book = getBookByISBN(isbn);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Retrieves a book from the database by its ISBN.
     * @param isbn The ISBN of the book to retrieve.
     * @return The book with the specified ISBN, or null if no book is found.
     */
    private static Book getBookByISBN(String isbn) {
        Book book = null;
        try {
            Connection connection = getBooksDBConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM titles WHERE isbn = ?");
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                int editionNumber = rs.getInt("editionNumber");
                String copyright = rs.getString("copyright");
                book = new Book(isbn, title, editionNumber, copyright);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    /**
     * Get all authors from the database
     * @return List of authors
     * @throws SQLException
     */
//    public static List<Author> getAllAuthors() throws SQLException {
//        LinkedList authorList = new LinkedList();
//
//        Connection connection = getBooksDBConnection();
//        Statement statement = connection.createStatement();
//        String sqlQuery = "SELECT * from " + DBProperties.AUTHOR_TABLE_NAME;
//
//        ResultSet resultSet = statement.executeQuery(sqlQuery);
//        while(resultSet.next()) {
//            authorList.add(
//                    new Author(
//                            resultSet.getInt(DBProperties.AUTHOR_COL_NAME_AUTHOR_ID),
//                            resultSet.getString(DBProperties.AUTHOR_COL_NAME_FIRST_NAME),
//                            resultSet.getString(DBProperties.AUTHOR_COL_NAME_LAST_NAME)
//                    )
//            );
//        }
//        return authorList;
//    }

    /**
     * Insert book into the database
     * @param book
     * @throws SQLException
     */
    public static void insertBook(Book book) throws SQLException {
        Connection connection = getBooksDBConnection();

        String sqlQuery = "INSERT INTO " + DBProperties.BOOK_TABLE_NAME +
                " VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        //The 4 values are the books attributes
        preparedStatement.setString(1, book.getIsbn());
        preparedStatement.setString(2, book.getTitle());
        preparedStatement.setInt(3, book.getEditionNumber());
        preparedStatement.setString(4, book.getCopyright());

        preparedStatement.executeQuery();

    }

    /**
     * Insert author into the database
     * @param author
     * @throws SQLException
     */
    public static void insertAuthor(Author author) throws SQLException {
        Connection connection = getBooksDBConnection();

        String sqlQuery = "INSERT INTO " + DBProperties.AUTHOR_TABLE_NAME +
                " VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        //The 3 values are the authors attributes
        preparedStatement.setInt(1, author.getAuthorID());
        preparedStatement.setString(2, author.getFirstName());
        preparedStatement.setString(3, author.getLastName());

        preparedStatement.executeQuery();
    }

    public static void insertIntoAuthorISBN(AuthorISBN isbn) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getBooksDBConnection();

            String sqlQuery = "INSERT INTO " + DBProperties.AUTHOR_ISBN_TABLE_NAME +
                    " VALUES (?,?)";
            preparedStatement = connection.prepareStatement(sqlQuery);

            //The 2 values are the authors attributes
            preparedStatement.setInt(1, isbn.getAuthorID());
            preparedStatement.setString(2, isbn.getIsbn());

            preparedStatement.executeQuery();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static int getlastaddedAuthorId() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int lastAuthorId = -1;

        try {
            connection = getBooksDBConnection();
            statement = connection.createStatement();
            String sqlQuery = "SELECT authorID FROM authors ORDER BY authorID DESC LIMIT 1";
            resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                lastAuthorId = resultSet.getInt("authorID");
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return lastAuthorId;
    }

    /**
     * Get a connection to the Books Database
     * @return connection
     * @throws SQLException
     */
    private static Connection getBooksDBConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            System.out.println("Option 1: Find the class worked!");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: unable to load driver class!");
        } catch(IllegalAccessException ex) {
            System.err.println("Error: access problem while loading!");
        } catch(InstantiationException ex){
            System.err.println("Error: unable to instantiate driver!");
        }

        return DriverManager.getConnection(DBProperties.BOOK_DB_URL, DBProperties.USER, DBProperties.PASS);
    }


}
