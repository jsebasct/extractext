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

        String repetetiveKey1 = "GET /search_alojamiento.php?product";
        String repetetiveKey2 = "GET /oportunidades";

        for (String line : fileLineText) {
            String[] temp = line.split("\"");

            String key;
            if (temp[1].contains("?")) {
                key = temp[1].split("\\?")[0];

            } else if (temp[1].startsWith(repetetiveKey1)) {
                key = repetetiveKey1;

            } else if (temp[1].startsWith(repetetiveKey2)) {
                key = repetetiveKey2;

            } else if (temp[1].contains("/")) {
                String[] keyByDash = temp[1].split("/");
                key = keyByDash[0] +"/"+ keyByDash[1] +"/"+ keyByDash[2];
            }
            else {
                key = temp[1];
            }

            result.put(key, temp[3]);
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
        result.put("1", "uno");
        result.put("2", "dos");
        result.put("2", "dos");
        result.put("3", "tres");
        result.put("3", "tres");

        result.put("/search_alojamiento.php?product=hotel&q=universal".split("\\?")[0], "2");
        result.put("/search_alojamiento.php?product=hotel&q=universal".split("\\?")[0], "2");
        result.put("GET /destacados/no-encontramos-resultados/24322309/ HTTP/1.1 -".split("\\?")[0], "2");

        printMap(result);
    }

    private void printMap(Map<String, String> map) {
        map.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }
}
