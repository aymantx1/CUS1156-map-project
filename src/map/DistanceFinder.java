/* Project: Project2_Map
 * Class: DistanceFinder.java
 * Modifer: Ayman Mohammed
 * Date: March 18, 2026
 * This class reads city connections from a file and computes
 * the shortest distances from a starting city to all other cities
 * using Dijkstra’s algorithm.
 */

package map;

import java.util.*;
import java.io.*;

public class DistanceFinder {

    private String startFrom;
    private Map<String, HashSet<DistanceTo>> directConnections;

    // Constructor: reads file and builds graph
    public DistanceFinder(String filename) {
        directConnections = new HashMap<String, HashSet<DistanceTo>>();

        try {
            Scanner file = new Scanner(new File(filename));
            boolean firstLine = true;

            while (file.hasNext()) {
                String city1 = file.next();
                String city2 = file.next();
                int distance = file.nextInt();

                // Set starting city from first line
                if (firstLine) {
                    startFrom = city1;
                    firstLine = false;
                }

                // Initialize cities if not already in map
                if (!directConnections.containsKey(city1)) {
                    directConnections.put(city1, new HashSet<DistanceTo>());
                }
                if (!directConnections.containsKey(city2)) {
                    directConnections.put(city2, new HashSet<DistanceTo>());
                }

                // Add bidirectional connections
                directConnections.get(city1).add(new DistanceTo(city2, distance));
                directConnections.get(city2).add(new DistanceTo(city1, distance));
            }

            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Returns the starting city
    public String getStartingCity() {
        return startFrom;
    }

    // Computes shortest distances using Dijkstra’s algorithm
    public Map<String, Integer> shortestDistances() {

        PriorityQueue<DistanceTo> pq = new PriorityQueue<DistanceTo>();
        Map<String, Integer> shortestKnownDistance = new TreeMap<String, Integer>();

        // Start from initial city
        pq.add(new DistanceTo(startFrom, 0));

        while (!pq.isEmpty()) {
            DistanceTo current = pq.remove();

            String city = current.getTarget();
            int distance = current.getDistance();

            // Skip if already visited
            if (shortestKnownDistance.containsKey(city)) {
                continue;
            }

            // Record shortest distance
            shortestKnownDistance.put(city, distance);

            // Explore neighbors
            for (DistanceTo neighbor : directConnections.get(city)) {
                int newDistance = distance + neighbor.getDistance();
                pq.add(new DistanceTo(neighbor.getTarget(), newDistance));
            }
        }

        return shortestKnownDistance;
    }
}