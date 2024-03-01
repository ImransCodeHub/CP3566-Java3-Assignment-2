package Java3.BookLibrary;

/**
 * Store all the MariaDB connection properties here.
 */
public class DBProperties {

    public static final String DB_URL = "jdbc:mariadb://localhost:3308";
    public static final String USER = "root";
    public static final String PASS = "JavaImran2024";
    public static final String JAVA_TEST_DB_URL = "jdbc:mariadb://localhost:3308/books?user=root&password=JavaImran2024";;

    //Book Table Information
    public static final String BOOK_DB_URL = "jdbc:mariadb://localhost:3308/books";
    public static final String BOOK_TABLE_NAME = "titles";
    public static final String BOOK_COL_NAME_ISBN = "isbn";
    public static final String BOOK_COL_NAME_TITLE = "title";
    public static final String BOOK_COL_NAME_EDITION_NUMBER = "editionNumber";
    public static final String BOOK_COL_NAME_COPYRIGHT = "copyright";

    //Author Table Information
    public static final String AUTHOR_TABLE_NAME = "authors";
    public static final String AUTHOR_COL_NAME_AUTHOR_ID = "authorID";
    public static final String AUTHOR_COL_NAME_FIRST_NAME = "firstName";
    public static final String AUTHOR_COL_NAME_LAST_NAME = "lastName";

    //authorISBN Table Information
    public static final String AUTHOR_ISBN_TABLE_NAME = "authorISBN";
    public static final String AUTHOR_ISBN_COL_NAME_AUTHOR_ID = "authorID";
    public static final String AUTHOR_ISBN_COL_NAME_ISBN = "isbn";
}
