import java.util.*;

public class Graph implements GraphInterface<Town, Road> {
    private Map<Town, List<Road>> adjacencyMap;

    public Graph() {
        adjacencyMap = new HashMap<>();
    }

    @Override
    public boolean addVertex(Town v) {
        if (!adjacencyMap.containsKey(v)) {
            adjacencyMap.put(v, new ArrayList<>());
            return true;
        }
        return false;
    }

    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        if (!adjacencyMap.containsKey(sourceVertex)) {
            addVertex(sourceVertex);
        }
        if (!adjacencyMap.containsKey(destinationVertex)) {
            addVertex(destinationVertex);
        }

        Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
        adjacencyMap.get(sourceVertex).add(newRoad);
        // Uncomment the line below for undirected graph
        // adjacencyMap.get(destinationVertex).add(new Road(destinationVertex, sourceVertex, weight, description));
        return newRoad;
    }

    @Override
    public boolean containsVertex(Town v) {
        return adjacencyMap.containsKey(v);
    }

    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        if (!adjacencyMap.containsKey(sourceVertex)) {
            return false;
        }
        return adjacencyMap.get(sourceVertex).stream()
                .anyMatch(road -> road.getDestination().equals(destinationVertex));
    }

    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        if (!adjacencyMap.containsKey(sourceVertex)) {
            return null;
        }

        Road toRemove = new Road(sourceVertex, destinationVertex, weight, description);
        if (adjacencyMap.get(sourceVertex).remove(toRemove)) {
            return toRemove;
        }
        return null;
    }

    @Override
    public boolean removeVertex(Town v) {
        if (!adjacencyMap.containsKey(v)) {
            return false;
        }

        adjacencyMap.remove(v);

        adjacencyMap.values().forEach(roads -> roads.removeIf(road -> road.getSource().equals(v) || road.getDestination().equals(v)));
        return true;
    }

    @Override
    public Set<Road> edgeSet() {
        Set<Road> allRoads = new HashSet<>();
        adjacencyMap.values().forEach(allRoads::addAll);
        return allRoads;
    }

    @Override
    public Set<Road> edgesOf(Town vertex) {
        if (!adjacencyMap.containsKey(vertex)) {
            return new HashSet<>();
        }
        return new HashSet<>(adjacencyMap.get(vertex));
    }

    @Override
    public Set<Town> vertexSet() {
        return new HashSet<>(adjacencyMap.keySet());
    }

    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        if (!adjacencyMap.containsKey(sourceVertex)) {
            return null;
        }
        return adjacencyMap.get(sourceVertex).stream()
                .filter(road -> road.getDestination().equals(destinationVertex))
                .findFirst()
                .orElse(null);
    }

    @Override
    
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        Map<Town, Integer> distances = new HashMap<>();
        PriorityQueue<Town> pq = new PriorityQueue<>(Comparator.comparing(distances::get));
        Map<Town, Road> previousRoads = new HashMap<>();
        Set<Town> visited = new HashSet<>();

        // Initialize distances
        for (Town town : adjacencyMap.keySet()) {
            distances.put(town, town.equals(sourceVertex) ? 0 : Integer.MAX_VALUE);
            pq.add(town);
        }

        while (!pq.isEmpty()) {
            Town currentTown = pq.poll();
            visited.add(currentTown);

            // Early exit if destination is reached
            if (currentTown.equals(destinationVertex)) {
                break;
            }

            List<Road> roads = adjacencyMap.get(currentTown);
            if (roads != null) {
                for (Road road : roads) {
                    Town neighbor = road.getDestination();
                    if (!visited.contains(neighbor)) {
                        int newDist = distances.get(currentTown) + road.getDistance();
                        if (newDist < distances.get(neighbor)) {
                            distances.put(neighbor, newDist);
                            previousRoads.put(neighbor, road);
                            pq.remove(neighbor);
                            pq.add(neighbor); // Update priority queue
                        }
                    }
                }
            }
        }

        return buildPath(destinationVertex, previousRoads);
    }

    private ArrayList<String> buildPath(Town destination, Map<Town, Road> previousRoads) {
        LinkedList<String> path = new LinkedList<>();
        Town current = destination;

        while (previousRoads.containsKey(current)) {
            Road road = previousRoads.get(current);
            Town source = road.getSource();

            String step = source.getName() + " via " + road.getName() + " to " + current.getName() + " " + road.getDistance() + " mi";
            path.addFirst(step);

            current = source;
        }

        return new ArrayList<>(path);
    }

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
        PriorityQueue<Town> pq = new PriorityQueue<>(Comparator.comparing(distances::get));
        Set<Town> visited = new HashSet<>();

        // Initialize distances and predecessors
        for (Town town : adjacencyMap.keySet()) {
            distances.put(town, town.equals(sourceVertex) ? 0 : Integer.MAX_VALUE);
            predecessors.put(town, null);
            pq.add(town);
        }

        while (!pq.isEmpty()) {
            Town currentTown = pq.poll();
            visited.add(currentTown);

            List<Road> roads = adjacencyMap.get(currentTown);
            if (roads != null) {
                for (Road road : roads) {
                    Town neighbor = road.getDestination();
                    if (!visited.contains(neighbor)) {
                        int newDist = distances.get(currentTown) + road.getDistance();
                        if (newDist < distances.get(neighbor)) {
                            distances.put(neighbor, newDist);
                            predecessors.put(neighbor, currentTown);
                            pq.remove(neighbor);
                            pq.add(neighbor); // Update priority queue
                        }
                    }
                }
            }
        }
    }
    
}

