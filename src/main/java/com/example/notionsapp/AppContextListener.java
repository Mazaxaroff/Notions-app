package com.example.notionsapp;

import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        NotionRepository notionRepository = new NotionRepository(new HashMap<>());
        JsonParserProcessor jsonParserProcessor = new JsonParserProcessor();
        JSONObject jsonObject = jsonParserProcessor.getFromFile();
        if (jsonObject.isEmpty()) {
            notionRepository.CreateNotion("Ваша первая заметка", "Напишите что-нибудь");
        } else notionRepository.setNotionMap(convert(jsonObject));
        context.setAttribute("allNotions", notionRepository);
    }

    private HashMap<UUID, Notion> convert(JSONObject jsonObject) {
        HashMap<UUID, Notion> result = new HashMap<>();
        HashMap<String, Object> jsonMap = (HashMap<String, Object>) jsonObject.toMap();
        for (String uuid : jsonMap.keySet()) {
            UUID uuidNotion = UUID.fromString(jsonObject.getJSONObject(uuid).getString("id"));
            String title = jsonObject.getJSONObject(uuid).getString("title");
            String text = jsonObject.getJSONObject(uuid).getString("text");
            LocalDateTime createDate = LocalDateTime.parse(jsonObject.getJSONObject(uuid).getString("createDate"));
            result.put(UUID.fromString(uuid), new Notion(uuidNotion, title, text, createDate));
        }
        return result;
    }

}

