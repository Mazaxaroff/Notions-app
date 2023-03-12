package com.example.notionsapp;

import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class NotionRepository {
    private Map<UUID, Notion> notionMap;
    private JsonParserProcessor jsonParser = new JsonParserProcessor();


    public NotionRepository(Map<UUID, Notion> notionMap) {
        this.notionMap = notionMap;
    }

    public void save(Notion notion) {
        if (notion != null) {
            notionMap.put(notion.getId(), notion);
            jsonParser.saveToFile(notionMap);
        } else {
            throw new IllegalArgumentException("notion can't be null");
        }
    }

    public void delete(UUID uuid) {
        if (uuid != null) {
            notionMap.remove(uuid);
            jsonParser.saveToFile(notionMap);
        } else {
            throw new IllegalArgumentException("uuid can't be null");
        }
    }

    public Notion CreateNotion(String title, String text) {
        Notion notion = new Notion(title, text);
        notionMap.put(notion.getId(), notion);
        jsonParser.saveToFile(notionMap);
        return notion;
    }
}
