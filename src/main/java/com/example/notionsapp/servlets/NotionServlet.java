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
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        notionRepository = (NotionRepository) getServletContext().getAttribute("allNotions");
        List<Notion> notions = (new ArrayList<>(notionRepository.getNotionMap().values()));
        req.setAttribute("notionsList", notions);

        req.getRequestDispatcher("/notions.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        switch (req.getParameter("mode")) {
            case "add":
                resp.sendRedirect("createNewNotion");
                break;
            case "delete":
                if (req.getParameter("uuid") != null) {
                    notionRepository.delete(UUID.fromString(req.getParameter("uuid")));
                    getServletContext().setAttribute("allNotions", notionRepository);
                }
                notionRepository.getNotionMap().values().forEach(notion -> System.out.println(notion.getTitle()));
                resp.sendRedirect("notions");
                break;
            case "open":
                getServletContext().setAttribute("onlyOpen", true);
                getServletContext().setAttribute("currentNotion", notionRepository
                        .getNotionMap()
                        .get(UUID.fromString(req.getParameter("uuid"))));

                resp.sendRedirect("currentNotion");
                break;
            case "edit":
                getServletContext().setAttribute("onlyOpen", false);
                getServletContext().setAttribute("currentNotion", notionRepository
                        .getNotionMap()
                        .get(UUID.fromString(req.getParameter("uuid"))));
                resp.sendRedirect("currentNotion");
                break;
        }
    }
}
