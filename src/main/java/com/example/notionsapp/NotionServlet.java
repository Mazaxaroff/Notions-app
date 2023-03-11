package com.example.notionsapp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "notionServlet", value = "/notions")
public class NotionServlet extends HttpServlet {
    private NotionRepository notionRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        notionRepository = (NotionRepository) servletContext.getAttribute("allNotions");
        notionRepository.CreateNotion("Ваша первая заметка", "Напишите что-нибудь");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Notion> notions = (new ArrayList<>(notionRepository.getNotionMap().values()));
        request.setAttribute("notionsList", notions);

        request.getRequestDispatcher("/WEB-INF/notions.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        switch (req.getParameter("mode")) {
            case "add":
                if (req.getParameter("newNotionTitle") != null
                        && req.getParameter("newNotionText") != null) {
                    notionRepository.save(new Notion(req.getParameter("newNotionTitle"), req.getParameter("newNotionText")));
                }
                resp.sendRedirect("notions");
                break;
            case "delete":
                if (req.getParameter("uuid") != null) {
                    notionRepository.delete(UUID.fromString(req.getParameter("uuid")));
                }
                resp.sendRedirect("notions");
                break;
            case "edit":
                resp.sendRedirect("notions?uuid=" + req.getParameter("uuid"));

        }
    }
}
