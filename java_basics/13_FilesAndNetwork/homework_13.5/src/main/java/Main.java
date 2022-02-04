import com.fasterxml.jackson.databind.ObjectMapper;
import core.Line;
import core.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String URL = "https://www.moscowmap.ru/metro.html#lines";
    private static Line[] lines;
    private static Station[] stations;
    private static Set<Set<Station>> connections;
    private static String outPath = "data\\data.json";

    public static void main(String[] args) throws IOException {
        Elements metroData = Jsoup.connect(URL).get().select("div#metrodata").first().child(0).children();
        lines = getLines(metroData);
        stations = getStations(metroData);
        connections = getConnections(metroData);
        writeData(jsonBuilder());
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());
            JSONArray jsonLines = (JSONArray) jsonData.get("lines");
            jsonLines.forEach(oLine -> {
                JSONObject lineJson = (JSONObject) oLine;
                String lineName = (String) lineJson.get("name");
                String lineId = (String) lineJson.get("lineId");
                int stationsCount = ((JSONArray) (((JSONObject) jsonData.get("stations")).get(lineId))).size();
                System.out.println(lineName + " состоит из " + stationsCount + " станций");
            });
            int connectionsCount = ((JSONArray) jsonData.get("connections")).size();
            System.out.println("Система московского метро (вместе с МЦД) имеет " +
                    connectionsCount + " переходных узлов");
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(outPath));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
    private static void writeData(JSONObject data) {
        try (FileWriter file = new FileWriter(outPath)) {
            ObjectMapper mapper = new ObjectMapper();
            file.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject jsonBuilder() {
        JSONObject result = new JSONObject();
        JSONArray jsonLines = new JSONArray();
        JSONObject jsonStations = new JSONObject();
        JSONArray jsonConnections = new JSONArray();
        Arrays.stream(lines).forEach(line -> {
            JSONObject jsonLine = new JSONObject();
            jsonLine.put("lineId", line.getId());
            jsonLine.put("name", line.getName());
            jsonLines.add(jsonLine);
            JSONArray jsonLineStations = new JSONArray();
            line.getStations().stream().forEach(station -> jsonLineStations.add(station.getName()));
            jsonStations.put(line.getId(), jsonLineStations);
        });
        connections.forEach(connection -> {
            JSONArray jsonConnectedStations = new JSONArray();
            connection.forEach(station -> {
                JSONObject jsonStation = new JSONObject();
                jsonStation.put("line", station.getLine().getId());
                jsonStation.put("name", station.getName());
                jsonConnectedStations.add(jsonStation);
            });
            jsonConnections.add(jsonConnectedStations);
        });
        result.put("lines", jsonLines);
        result.put("stations", jsonStations);
        result.put("connections", jsonConnections);
        return result;
    }

    private static Set<Set<Station>> getConnections(Elements metroData) {
        HashSet<Set<Station>> connections = new HashSet<>();
        metroData.select("div.js-metro-stations").forEach(line -> {
            String fromLineId = line.attr("data-line");
            line.children().forEach(station -> {
                Station fromStation = getStation(station.select("span.name").text(), fromLineId);
                Set<Station> currentConnetions = new HashSet<>();
                station.select("span[class*=t-icon-metroln]")
                        .forEach(o -> {
                            String toLineId = o.className().split("ln-")[1];
                            String toStationName = o.attr("title").split("«")[1].split("»")[0];
                            Station toStation = getStation(toStationName, toLineId);
                            currentConnetions.add(toStation);
                        });
                if (!currentConnetions.isEmpty()) {
                    currentConnetions.add(fromStation);
                    connections.add(currentConnetions);
                }
            });
        });
        return connections;
    }

    public static Line getLine(String id) {
        for (Line line : lines)
            if (line.getId().equals(id))
                return line;
        return null;
    }

    public static Station getStation(String name, String lineId) {
        for (Station station : stations)
            if (station.getName().equals(name) && station.getLine().getId().equals(lineId))
                return station;
        return null;
    }

    public static Line[] getLines(Elements metroData) {
        List<Line> linesList;
        linesList = metroData.select("span.js-metro-line").stream()
                .map((line) -> new Line(line.attr("data-line"), line.text())).collect(Collectors.toList());
        return linesList.toArray(new Line[linesList.size()]);
    }

    public static Station[] getStations(Elements metroData) {
        List<Station> stationsList = new ArrayList<>();
        metroData.select("div.js-metro-stations").forEach(line -> {
            Line currentLine = getLine(line.attr("data-line"));
            line.children().forEach(station -> {
                String stationName = station.select("span.name").text();
                Station currentStation = new Station(stationName, currentLine);
                currentLine.addStation(currentStation);
                stationsList.add(currentStation);
            });
        });
        return stationsList.toArray(new Station[stationsList.size()]);
    }

}
