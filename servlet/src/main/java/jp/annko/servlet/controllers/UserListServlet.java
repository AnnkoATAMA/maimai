package jp.annko.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/users")
public class UserListServlet extends HttpServlet {
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/maimaidb?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");

        if (email == null || !isAdmin(email)) {
            resp.sendRedirect("../login.jsp");
            return;
        }

        List<String> users = new ArrayList<>();

        try {
            Class.forName(MYSQL_DRIVER);
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement("SELECT email FROM users")) {

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    users.add(rs.getString("email"));
                }

                req.setAttribute("users", users);
                req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
            } catch (SQLException e) {
                throw new ServletException("Database error occurred.", e);
            }
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC driver not found.", e);
        }
    }

    private boolean isAdmin(String email) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT role FROM users WHERE email = ?")) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return "ADMIN".equals(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

