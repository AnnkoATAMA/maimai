<%@page import="jp.annko.servlet.controllers.Syogi"%>
<%@page import="jp.annko.servlet.controllers.Json"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Syogi</title>
    <link rel="stylesheet" type="text/css" href="./static/css/syogi.css?v=1">
    <script src="./static/script/syogi.js"></script>
</head>
<body>
<from>
<div class="back-obi">
	<h1 class="obi">将棋</h1>
	<div class="menu">
		<a href = "logout.jsp">・メニューへ戻る</a>
		<a href = "welcome.jsp">・ログアウト</a>
	</div>
</div>
<%
	Syogi syogi = new Syogi();
	Json json = new Json();
	
	int height = 540/9;
	int width = 540/9;
	List<List<Integer>> bord = syogi.initaialData();
	List<List<List<Integer>>> pieceMoveData = syogi.pieceMoveData();
	String bordData = json.parseJson(bord);
	String jsonMoveData = json.parseJsonThree(pieceMoveData);
	List<List<Integer>> place = new ArrayList<>();
	for(int i = 0;i < 2;i++){
		List<Integer> line = new ArrayList<>();
		for(int j = 0;j < 9;j++){
			line.add(0);
		}
		place.add(line);
	}
%>
<div class="bord">
	<%
		for(int i = 0;i <= 9;i++){
	%>
		<div style="background-color: #000; position: absolute;
		width: 540px; height: 1px; top:<%=i * height %>px;"></div>
		<div style="background-color: #000; position: absolute;
		height: 540px; width: 1px; left:<%=i * width %>px;"></div>
	<%
		}
	%>
	
	<%
		for(int i = 0;i < 9;i++){
	%>
			<button class="place" style="
				top: <%=i * height %>px; left: -60px;
				transform: rotate(180deg)"
				data-i="<%=9 %>" data-j="<%=i %>"
				data-bord="<%=bordData %>"
				data-place="<%=place %>"
                onclick="highlightMove(9, <%=i %>,
                '<%=jsonMoveData %>',
                <%=2 %>,
                '<%=bordData %>')">
					<%=syogi.pieceName(8-i)%>
			</button>
			
			<button class="place" style="
				top: <%=i * height %>px; left: 540px;"
				data-i="<%=10 %>" data-j="<%=i %>"
				data-bord="<%=bordData %>"
				data-place="<%=place %>"
                onclick="highlightMove(10, <%=i %>,
                '<%=jsonMoveData %>',
                <%=2 %>,
                '<%=bordData %>')">
					<%=syogi.pieceName(i)%>
			</button>
	<%
			for(int j = 0;j < 9;j++){
				int pieceNumber = bord.get(i).get(j);
				int isFoe = bord.get(i+11).get(j);

	%>
	
			<button class="piece" style="
				top: <%=i * height%>px; left: <%=j * width%>px;
				transform: rotate(<%=syogi.isFriendOrFoe(bord.get(i+11).get(j)) %>deg)"
				data-i="<%=i %>" data-j="<%=j %>"
				data-move="<%=jsonMoveData %>" data-isFoe="<%=isFoe %>"
				data-bord="<%=bordData %>"
                onclick="highlightMove(<%=i %>, <%=j %>,
                '<%=jsonMoveData %>',
                <%=isFoe %>,
                '<%=bordData %>')">
					<%=syogi.pieceName(pieceNumber) %>
			</button>
	<%
			}
		}
	%>
</div>
	
</from>
</body>
</html>