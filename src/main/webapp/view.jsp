<%@ page import="java.io.File" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
            crossorigin="anonymous"></script>
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <title>View</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="container">
                <%
                    String path = "img" + File.separator;
                %>
                <p class="imgP"><img src="<%=path%>${picture.getLink()}" class="img card-img-top" alt="..."></p>
                <div class="card-body">
                    <h5 class="card-title">${picture.getName()}</h5>
                    <p class="card-text">Год: ${picture.getYear()}, Автор: ${picture.getAuthor()}</p>
                    <p class="card-text">Место хранения: ${picture.getStorage()}, Цена: ${picture.getPrice()}</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
