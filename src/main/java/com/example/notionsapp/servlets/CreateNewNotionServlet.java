package com.example.notionsapp.servlets;

import com.example.notionsapp.Notion;
import com.example.notionsapp.NotionRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "createNewNotionServlet", value = "/createNewNotion")
public class CreateNewNotionServlet extends HttpServlet {
    private NotionRepository notionRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        notionRepository = (NotionRepository) servletContext.getAttribute("allNotions");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/createNewNotion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        if (req.getParameter("newNotionsTitle") != null
                && req.getParameter("newNotionsText") != null) {
            notionRepository.save(new Notion(req.getParameter("newNotionsTitle"), req.getParameter("newNotionsText")));
            getServletContext().setAttribute("allNotions", notionRepository);
        }
        resp.sendRedirect("notions");
    }
}
