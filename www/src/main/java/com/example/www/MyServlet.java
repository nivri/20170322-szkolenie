package com.example.www;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/*")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String foo = req.getParameter("foo");
        System.out.println("foo = " + foo);

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.print("<b>Przeslano</b>: " + foo);
        writer.flush();
    }
}
