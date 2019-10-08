package org.la.chin;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TextReader {


    private String fileName = "/home/scruz/Documents/grepeando/qv-home.log";


    public String getFilteredText() {
        List<String> fileLineText = getFileAsList();

        Map<String, String> result = new HashMap<>();
        for (String line : fileLineText) {
            String[] temp = line.split("\"");
            result.put(temp[1], temp[3]);
        }

        printMap(result);

        return "";
    }

    private List<String> getFileAsList() {
        List<String> list = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            //br returns as stream and convert it into a List
            list = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void printSample() {
        Map<String, String> result = new HashMap<>();
        result.put("1", "uno");
        result.put("2", "dos");
        result.put("2", "dos");
        result.put("2", "dos");
        result.put("3", "tres");
        printMap(result);
    }

    private void printMap(Map<String, String> map) {
        map.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }
}
