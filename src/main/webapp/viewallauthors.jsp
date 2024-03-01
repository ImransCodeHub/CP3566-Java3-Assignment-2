<%--
  Created by IntelliJ IDEA.
  User: Imran Moin
  Date: 2024-03-01
  Time: 5:03 a.m.
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="java.util.List" %>
<%@ page import="Java3.BookLibrary.Author" %>
<%@ page import="Java3.BookLibrary.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

Authors dump
<% List<Author> authorList = (List<Author>) request.getAttribute("authorlist"); %>


<%
    for (Author author : authorList) {
        out.println("Author ID: " + author.getAuthorID() + "<br>");
        out.println("Name: " + author.getFirstName() + " " + author.getLastName() + "<br>");
        out.println("Books:" + author.getBookList().size() +  "<br>");
        for (Book book: author.getBookList()) {
            out.println("- " + book.getTitle() + "<br>");
        }
    }
%>

</body>

<a href="index.jsp">Back to Main Menu</a>

</html>
