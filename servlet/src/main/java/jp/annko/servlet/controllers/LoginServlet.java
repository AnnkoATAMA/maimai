package jp.annko.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/maimaiDB");
        } catch (NamingException e) {
            throw new ServletException("Cannot find JNDI resource", e);
        }
    }

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

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Username and password are required.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        String hashedPassword = hashPassword(password);

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM users WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                req.getSession().setAttribute("username", username);
                resp.sendRedirect("welcome.jsp");
            } else {
                req.setAttribute("error", "Invalid username or password.");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error occurred.", e);
        }
    }

    private String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}