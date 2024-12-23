package jp.annko.maimai;

import jp.annko.maimai.controllers.LoginServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        handler.setContextPath("/");
        handler.addServlet(LoginServlet.class, "/login");
        server.setHandler(handler);

        System.out.println("Starting server on http://localhost:8080/login...");
        System.out.println("Starting server on https://maimai.annko.jp/login...");
        server.start();
        server.join();
    }
}
