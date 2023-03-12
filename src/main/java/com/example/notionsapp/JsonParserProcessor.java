package com.example.notionsapp;

import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class JsonParserProcessor {
    private JSONObject jsonpObject;
    File file;

    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource("notions.json");

    public JsonParserProcessor() {
        init();
    }

    private void init() {

        try {
            this.file = new File(resource.getFile());
        } catch (Exception e) {
            this.file = new File("notions.json");
        }
    }


    public void saveToFile(Map<UUID, Notion> map) {
        jsonpObject = new JSONObject(map);
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            out.write(jsonpObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject getFromFile() {
        if (file.isFile()) {
            try {
                InputStream is = new FileInputStream(this.file);
                String jsonTxt = new BufferedReader(new InputStreamReader(is))
                        .lines().collect(Collectors.joining("\n"));
                JSONObject json = new JSONObject(jsonTxt);
                return json;
            } catch (IOException e) {
                System.out.println("file not found");
                return new JSONObject();
            }
        }
        return new JSONObject();
    }
}




