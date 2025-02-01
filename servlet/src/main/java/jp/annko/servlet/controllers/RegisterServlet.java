package jp.annko.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/maimaidb?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Email and password are required.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        try {
            Class.forName(MYSQL_DRIVER);
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO users (email, password, role) VALUES (?, ?, 'USER')")) {

                stmt.setString(1, email);
                stmt.setString(2, password);
                stmt.executeUpdate();

                resp.sendRedirect("login.jsp");
            } catch (SQLException e) {
                throw new ServletException("Database error occurred.", e);
            }
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC driver not found.", e);
        }
    }
}

