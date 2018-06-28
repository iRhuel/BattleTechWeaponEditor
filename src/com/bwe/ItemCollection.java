package com.bwe;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ItemCollection {
    private ObjectMapper mapper;
    private ObjectWriter writer;
    private ArrayList<File> fileArrayList = new ArrayList<>();
    private ArrayList<JsonNode> jsonNodeArrayList = new ArrayList<>();

    public ItemCollection(String path) {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        mapper = new ObjectMapper();
        writer = mapper.writer(printer);

        search(new File(path));
        List<String> tagList = new LinkedList<>();

        for (File file : fileArrayList) {
            try {
                if (!file.getName().contains("Template") && file.getName().endsWith(".json")) {
                    JsonNode jsonNode = mapper.readTree(file);
                    if (jsonNode.get("ComponentTags").get("tagSetSourceFile") != null)
//                        for (JsonNode node : jsonNode.get("ComponentTags").get("tagSetSourceFile"))
                            if (!tagList.contains(jsonNode.get("ComponentTags").get("tagSetSourceFile").asText()))
                                tagList.add(jsonNode.get("ComponentTags").get("tagSetSourceFile").asText());
                    jsonNodeArrayList.add(jsonNode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (String str : tagList)
            System.out.println(str);
    }

    private void search(File baseFile) {
        File[] fileList = baseFile.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isDirectory()) {
                    search(file);
                } else {
                    fileArrayList.add(file);
                }
            }
        }
    }

    public ObservableList getObservableFiles() {
        return FXCollections.observableList(fileArrayList);
    }
}
