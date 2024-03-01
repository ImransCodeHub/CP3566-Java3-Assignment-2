<%--
  Created by IntelliJ IDEA.
  User: Imran Moin
  Date: 2024-03-01
  Time: 5:54 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Author to the Database!</title>
</head>
<body>

<h1>Add a Author!</h1>

<form action = "library-data" method = "POST">
    <label>Author Name:</label>
    First Name: <input type="text" name="first_name">
    Last Name: <input type="text" name="last_name">
    <input type="hidden" id="view" name="view" value="add_author">
    <input type="submit" value="Add Author">
</form>

</br>
<a href="index.jsp">Back to Main Menu</a>

</body>
</html>
