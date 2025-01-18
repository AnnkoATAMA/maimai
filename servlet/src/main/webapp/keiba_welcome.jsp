<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String username = (String) session.getAttribute("username");
  if (username == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>
<html>
<head>
  <title>馬券ジェネレーター</title>
  <link rel="stylesheet" type="text/css" href="./static/css/keiba_welcome.css">
</head>
<body>
<h1>こちらは馬券ジェネレーター(単勝のみ)です。</h1>

<form action="keiba.jsp" method="post">
  <label for="racecourse">開催競馬場:</label>
  <input type="text" id="racecourse" name="racecourse" required>

  <label for="betType">何にかけるか:</label>
  <input type="text" id="betType" name="betType" required>

  <input type="submit" value="馬券を生成">
</form>

</body>
</html>
