package jp.annko.servlet.controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoDAO {
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/maimaidb?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    static {
        try {
            Class.forName(MYSQL_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBCドライバのロードに失敗しました", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public List<Video> getAllVideos() throws SQLException {
        List<Video> videos = new ArrayList<>();
        String sql = "SELECT * FROM videos ORDER BY id";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Video video = new Video(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("url"),
                        rs.getString("description")
                );
                videos.add(video);
            }
        }
        return videos;
    }

    public void addVideo(Video video) throws SQLException {
        String sql = "INSERT INTO videos (title, url, description) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, video.getTitle());
            pstmt.setString(2, video.getUrl());
            pstmt.setString(3, video.getDescription());
            pstmt.executeUpdate();
        }
    }

    public void deleteVideo(int id) throws SQLException {
        String sql = "DELETE FROM videos WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public Video getVideoById(int id) throws SQLException {
        String sql = "SELECT * FROM videos WHERE id = ?";
        Video video = null;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    video = new Video(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("url"),
                            rs.getString("description")
                    );
                }
            }
        }
        return video;
    }

    public void updateVideo(Video video) throws SQLException {
        String sql = "UPDATE videos SET title = ?, url = ?, description = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, video.getTitle());
            pstmt.setString(2, video.getUrl());
            pstmt.setString(3, video.getDescription());
            pstmt.setInt(4, video.getId());
            pstmt.executeUpdate();
        }
    }
}
