package jp.annko.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    // JDBC接続情報（MySQL用に修正）
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver"; // MySQLのドライバ
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/maimaidb?useSSL=false&serverTimezone=UTC"; // MySQLのURL
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String view = "/login.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // ユーザー名とパスワードが空でないかを確認
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Username and password are required.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        // データベース接続と認証処理
        try {
            // JDBCドライバをロード
            Class.forName(MYSQL_DRIVER);

            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(
                         "SELECT * FROM users WHERE username = ? AND password = ?")) {

                stmt.setString(1, username);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // ログイン成功
                    req.getSession().setAttribute("username", username);
                    resp.sendRedirect("welcome.jsp");
                } else {
                    // ログイン失敗
                    req.setAttribute("error", "Invalid username or password.");
                    System.out.println("Invalid username or password.");
                    System.out.println("username: " + username);
                    System.out.println("password: " + password);
                    System.out.println(HttpServletResponse.SC_UNAUTHORIZED);
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                throw new ServletException("Database error occurred.", e);
            }
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC driver not found.", e);
        }
    }
}
