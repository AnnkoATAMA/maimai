<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="./static/css/style.css">
    <link rel="stylesheet" type="text/css" href="./static/css/register.css">
</head>
<body>
<div class="container">
    <h1>Register</h1>
    <form action="register" method="post" class="register_form card">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button type="submit">Register</button>
    </form>


    <p style="color:red;">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
    </p>
</div>
</body>
</html>
