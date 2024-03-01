<%--
  Created by IntelliJ IDEA.
  User: Imran Moin
  Date: 2024-03-01
  Time: 5:53 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Java3.BookLibrary.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="Java3.BookLibrary.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View all Books</title>
</head>
<body>

Book dump!

<!-- book list attribute is available --->

<% List<Book> bookList =  (List<Book>) request.getAttribute("booklist"); %>

<table>
    <tr>
        <th>ISBN</th>
        <th>Title</th>
        <th>Edition</th>
        <th>Copyright</th>
        <th>Author:</th>
    </tr>

    <%
        for (Book book: bookList) {
            out.println("<tr>");
            out.println("<td>" + book.getIsbn() + "</td>");
            out.println("<td>" + book.getTitle() + "</td>");
            out.println("<td>" + book.getEditionNumber() + "</td>");
            out.println("<td>" + book.getCopyright() + "</td>");
            for (Author author: book.getAuthorList()){
                out.println("<td>" + author.getFirstName() + " " + author.getLastName() + "</td>");
                 }
            out.println("</tr>");
        }

    %>

</table>

<a href="index.jsp">Back to Main Menu</a>

</body>
</html>
