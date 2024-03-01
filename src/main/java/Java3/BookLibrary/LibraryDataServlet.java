package Java3.BookLibrary;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static Java3.BookLibrary.DBManager.getlastaddedAuthorId;

@WebServlet(name = "libraryData", value = "/library-data")
public class LibraryDataServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String view = request.getParameter("view");

        if (view.equals("books")) {
            List<Book> bookList = null;
            bookList = DBManager.getAllBooks();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewallbooks.jsp");
            request.setAttribute("booklist", bookList);

            requestDispatcher.forward(request, response);

        } else if (view.equals("authors")) {
            List<Author> authorList = null;
            authorList = DBManager.getAllAuthors();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewallauthors.jsp");
            request.setAttribute("authorlist", authorList);

            requestDispatcher.forward(request, response);

        } else {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String view = request.getParameter("view");

        if(view.equals("add_book")){
            String isbn = request.getParameter("isbn");
            String title = request.getParameter("title");
            int editionNumber = Integer.valueOf(request.getParameter("edition_number"));
            String copyright = request.getParameter("copyright");

            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");

            try {
                DBManager.insertBook(
                        new Book(
                                request.getParameter("isbn"),
                                request.getParameter("title"),
                                Integer.valueOf(request.getParameter("edition_number")),
                                request.getParameter("copyright")
                        ));
                DBManager.insertAuthor(
                        new Author(
                                0,
                                request.getParameter("first_name"),
                                request.getParameter("last_name")
                        ));

                // Retrieve last added author ID
                int lastAuthorId = DBManager.getlastaddedAuthorId();

                DBManager.insertIntoAuthorISBN(
                        new AuthorISBN(
                                lastAuthorId,
                                isbn
                        ));

                response.getWriter().println("<script>alert('Book added successfully!'); window.location.href = 'index.jsp';</script>");

            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp?errorMessage=" + e.getMessage());
            }

        } else if(view.equals("add_author")){
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");

            try {
                DBManager.insertAuthor(
                        new Author(
                                0,
                                request.getParameter("first_name"),
                                request.getParameter("last_name")
                        ));
                response.getWriter().println("<script>alert('Author added successfully!'); window.location.href = 'index.jsp';</script>");


            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp?errorMessage=" + e.getMessage());
            }

        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
