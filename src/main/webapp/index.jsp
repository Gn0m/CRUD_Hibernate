<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
            crossorigin="anonymous"></script>
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <meta charset="UTF-8">
    <title>Gallery</title>
</head>
<body>
<div class="container">
    <h2>Gallery List</h2>
    <p><a href='<c:url value="/create" />'>Create new</a></p>
    <table>
        <tr>
            <th class="col1">Name</th>
            <th class="col1">Author</th>
            <th class="col1">Year</th>
            <th class="col1">Storage</th>
            <th class="col1">Price</th>
        </tr>
        <c:forEach var="item" items="${gallery}">
            <tr>
                <td>${item.getName()}</td>
                <td>${item.getAuthor()}</td>
                <td>${item.getYear()}</td>
                <td>${item.getStorage()}</td>
                <td>${item.getPrice()}</td>
                <td>
                    <a href='<c:url value="/edit?id=${item.getId()}" />'>Edit</a>
                    <a href='<c:url value="/view?id=${item.getId()}" />'>View</a>
                    <form method="post" action='<c:url value="/delete" />' style="display:inline;">
                        <input type="hidden" name="id" value="${item.getId()}">
                        <input type="hidden" name="link" value="${item.getLink()}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>