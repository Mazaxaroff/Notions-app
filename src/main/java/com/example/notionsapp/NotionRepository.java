package com.example.notionsapp;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.UUID;

@Data
public class NotionRepository {
    private static final Logger LOGGER = LogManager.getLogger(NotionRepository.class);
    private Map<UUID, Notion> notionMap;

    public NotionRepository(Map<UUID, Notion> notionMap) {
        this.notionMap = notionMap;
    }

    public void save(Notion notion){
        if (notion!=null){
            notionMap.put(notion.getId(),notion);
        } else {
            LOGGER.error("notion is null");
            throw new IllegalArgumentException("notion can't be null");
        }
    }

    public void delete(UUID uuid){
        if (uuid!=null){
            notionMap.remove(uuid);
        } else {
            LOGGER.error("uuid is null");
            throw new IllegalArgumentException("uuid can't be null");
        }
    }

    public Notion CreateNotion(String title, String text) {
        Notion notion = new Notion(title, text);
        notionMap.put(notion.getId(), notion);

        LOGGER.info("Notion " + notion.getTitle() + " was created");
        return notion;
    }
}
