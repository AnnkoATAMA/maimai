<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="./static/css/style.css">
    <link rel="stylesheet" type="text/css" href="./static/css/login.css">
</head>
<body>
<div class="container">
    <h1>Login</h1>
    <div>
        <form action="login" method="post" class="login_form card">
            <label for="username">Username:</label>
            <input type="text" id="username" autocomplete="username" name="username" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" autocomplete="current-password" name="password" required>
            <br>
            <button type="submit">Login</button>
        </form>
    </div>

    <p style="color:red;">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
    </p>
</div>

</body>
</html>
