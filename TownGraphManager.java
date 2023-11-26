import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TownGraphManager implements TownGraphManagerInterface {
    private Graph graph;

    public TownGraphManager() {
        graph = new Graph();
    }
    
    @Override
    public boolean addTown(String v) {
        return graph.addVertex(new Town(v));
    }

    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        Town source = new Town(town1);
        Town destination = new Town(town2);

        if (!graph.containsVertex(source) || !graph.containsVertex(destination)) {
            return false;
        }

        Road newRoad = graph.addEdge(source, destination, weight, roadName);
        return newRoad != null;
    }

    @Override
    public String getRoad(String town1, String town2) {
        Road road = graph.getEdge(new Town(town1), new Town(town2));
        return road == null ? null : road.getName();
    }

    @Override
    public boolean deleteRoadConnection(String town1, String town2, String roadName) {
        Town source = new Town(town1);
        Town destination = new Town(town2);

        if (!graph.containsVertex(source) || !graph.containsVertex(destination)) {
            return false;
        }

        Road roadToDelete = graph.getEdge(source, destination);
        if (roadToDelete == null || !roadToDelete.getName().equals(roadName)) {
            return false;
        }

        return graph.removeEdge(source, destination, roadToDelete.getDistance(), roadName) != null;
    }

    @Override
    public boolean deleteTown(String v) {
        return graph.removeVertex(new Town(v));
    }

    @Override
    public boolean containsTown(String v) {
        return graph.containsVertex(new Town(v));
    }

    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        return graph.containsEdge(new Town(town1), new Town(town2));
    }

    @Override
    public ArrayList<String> allRoads() {
        Set<Road> roads = graph.edgeSet();
        ArrayList<String> roadNames = new ArrayList<>();
        for (Road road : roads) {
            roadNames.add(road.getName());
        }
        Collections.sort(roadNames);
        return roadNames;
    }

    @Override
    public ArrayList<String> allTowns() {
        Set<Town> towns = graph.vertexSet();
        ArrayList<String> townNames = new ArrayList<>();
        for (Town town : towns) {
            townNames.add(town.getName());
        }
        Collections.sort(townNames);
        return townNames;
    }

    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        return graph.shortestPath(new Town(town1), new Town(town2));
    }

    @Override
    public Town getTown(String name) {
        for (Town town : graph.vertexSet()) {
            if (town.getName().equals(name)) {
                return town;
            }
        }
        return null;
    }

    public void populateTownGraph(File selectedFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(selectedFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");

                if (parts.length != 4) {
                    continue; // Skip malformed lines
                }

                String town1 = parts[0].trim();
                String town2 = parts[1].trim();
                int distance;
                try {
                    distance = Integer.parseInt(parts[2].trim());
                } catch (NumberFormatException e) {
                    continue; // Skip lines with invalid distance
                }
                String roadName = parts[3].trim();

                addTown(town1);
                addTown(town2);
                addRoad(town1, town2, distance, roadName);
            }
   
    }

}
}