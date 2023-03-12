package com.example.notionsapp.servlets;

import com.example.notionsapp.Notion;
import com.example.notionsapp.NotionRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "currentNotionServlet", value = "/currentNotion")
public class CurrentNotionServlet extends HttpServlet {
    private Notion currentNotion;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        currentNotion = (Notion) getServletContext().getAttribute("currentNotion");
        req.setAttribute("title", currentNotion.getTitle());
        req.setAttribute("text", currentNotion.getText());

        req.getRequestDispatcher("/currentNotion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        currentNotion.setTitle(req.getParameter("notionsTitle"));
        currentNotion.setText(req.getParameter("notionsText"));
        NotionRepository notionRepository = (NotionRepository) getServletContext().getAttribute("allNotions");
        notionRepository.save(currentNotion);
        resp.sendRedirect("notions");
    }
}
