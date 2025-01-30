<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>マイプレイリスト</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/playlist.css">
</head>
<body>
    <div class="container">
        <h1>マイプレイリスト</h1>
        
        <div class="video-form">
            <h2>新しい動画を追加</h2>
            <form action="playlist" method="post">
                <input type="hidden" name="action" value="add">
                <input type="text" name="title" placeholder="タイトル" required><br>
                <input type="text" name="url" placeholder="YouTube URL" required><br>
                <input type="text" name="description" placeholder="説明"><br>
                <button type="submit">追加</button>
            </form>
        </div>
        
        <ul class="video-list">
            <c:forEach var="video" items="${videos}">
                <li class="video-item">
                    <div class="video-title">${video.title}</div>
                    <div class="video-description">${video.description}</div>
                    <div class="video-player">
                        <iframe width="560" height="315" 
                                src="${video.url}" 
                                frameborder="0" 
                                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                                allowfullscreen>
                        </iframe>
                    </div>
                    <form action="playlist" method="post" class="delete-form">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${video.id}">
                        <button type="submit" class="delete-btn">削除</button>
                    </form>
                </li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>