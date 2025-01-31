<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" type="text/css" href="./static/css/style.css">
</head>
<body>
<div class="container">
    <h1><%= "Hello World!" %>
    </h1>
    <br/>
    <div class="card">
        <nav class="menu">
            <ul>
                <li><a href="login.jsp">login</a></li>
                <li><a href="hello-servlet">Hello Servlet</a></li>
                <li><a href="register.jsp">新規登録</a></li>
            </ul>
        </nav>
    </div>

</div>
</body>
</html>