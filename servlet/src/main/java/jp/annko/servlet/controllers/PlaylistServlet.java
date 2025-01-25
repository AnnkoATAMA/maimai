package jp.annko.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/playlist")
public class PlaylistServlet extends HttpServlet {
    private VideoDAO videoDAO = new VideoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            List<Video> videos = videoDAO.getAllVideos();

            req.setAttribute("videos", videos);

            String view = "/playlist.jsp";
            RequestDispatcher dispatcher = req.getRequestDispatcher(view);
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("動画リストの取得中にエラーが発生しました。", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {

                Video video = new Video(
                        0,
                        request.getParameter("title"),
                        request.getParameter("url"),
                        request.getParameter("description")
                );
                videoDAO.addVideo(video);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                videoDAO.deleteVideo(id);
            }
            response.sendRedirect(request.getContextPath() + "/playlist");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "無効なID形式が指定されました。");
            doGet(request, response);
        } catch (SQLException e) {
            throw new ServletException("データベース操作中にエラーが発生しました。", e);
        }
    }
}
