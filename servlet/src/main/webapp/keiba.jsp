<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String username = (String) session.getAttribute("username");
  if (username == null) {
    response.sendRedirect("login.jsp");
    return;
  }

  java.util.Date date = new java.util.Date();
  java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy年MM月dd日");
  String currentDate = sdf.format(date);

  String currentRaceCourse = request.getParameter("racecourse");
  String currentBetType = request.getParameter("betType");
%>
<html>
<head>
  <title>馬券ジェネレーター</title>
    <link rel="stylesheet" type="text/css" href="./static/css/style.css">
  <link rel="stylesheet" type="text/css" href="./static/css/keiba.css">
</head>
<body>
<div class="ticket">
  <div class="ticket_back"></div>
  <div class="ticket_text">
    <div class="race_info_top">
      <p class="race_date"><%= currentDate %></p>
      <p class="race_course"><%= currentRaceCourse %></p>
      <div class="race_race">
        <p class="race_num">10</p><p class="race__">レース</p>
      </div>
    </div>
    <div class="qr_box">
      <div class="qr1">a</div>
      <div class="qr2">a</div>
    </div>
    <div class="race_info_bottom">
      <p class="race_info_bottom_race">Servlet</p>
      <p class="race_info_bottom_st" >AnnkoATAMA</p>
      <p class="race_info_bottom_date"><%= currentDate %></p>
    </div>
    <p class="race_rara1">https://github.com/AnnkoATAMA/maimai</p>
    <p class="race_rara2">loginkinoutaihensugita</p>
  </div>
  <div class="ticket_win">
    <div class="win_box">
      <p class="win_type1">WIN</p>
      <h1 class="win_name1">単</h1>
      <h1 class="win_name2">勝</h1>
      <p class="win_type2">WIN</p>
    </div>
  </div>
  <div class="ticket_horse">
    <div class="horse_name_ticket">
      <div class="horse_list">
        <p class="horse_num">1</p>
        <h1 class="horse_name"><%= currentBetType %><p class="horse_star">☆ ☆ ☆</p></h1><h1 class="horse_yen">1000<p class="horse__yen">love</p></h1>
      </div>
    </div>
  </div>
</div>
</body>
</html>
