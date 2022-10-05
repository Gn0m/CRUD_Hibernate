<%--
  Created by IntelliJ IDEA.
  User: ilyan
  Date: 04.10.2022
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
            crossorigin="anonymous"></script>
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <title>Create</title>
</head>
<body>
<div class="container">
    <h3>New Imgage</h3>
    <form method="post" action="${pageContext.request.contextPath}/create" enctype="multipart/form-data"
          style="margin-top: 50px">
        <label>Name</label><br>
        <input name="name"/><br><br>
        <label>Author</label><br>
        <input name="author"/><br><br>
        <label>Year</label><br>
        <input name="year"/><br><br>
        <label>Storage</label><br>
        <input name="storage"/><br><br>
        <label>Price</label><br>
        <input name="price" type="number"/><br><br>
        <input type="file" name="file" value="select images..."/>
        <input type="submit" value="Save" />
    </form>
</div>
</body>
</html>
