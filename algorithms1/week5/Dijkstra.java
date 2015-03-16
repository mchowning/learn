package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by matt on 2/16/15.
 */
public class Dijkstra {

    public static class Vertex {
        private int id;
        private List<Edge> edges = new ArrayList<Edge>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addEdge(Edge e) {
            edges.add(e);
        }
    }

    public static class Edge {
        private int startVertex;
        private int endVertex;
        private int length;

        public Edge(int startVertex, int endVertex, int length) {
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.length = length;
        }
    }

    private static List<Vertex> vertices;
    private static int[] goalVertices;
    private static boolean[] processedVertices;
    private static int[] shortestPathDistances;

    public static void main(String[] args) throws Exception {
        vertices = getVerticesFromFile(args[0]);
        for (int i = 1; i < args.length; i++) {
            int sourceVertex = 1;
            int goalVertex = Integer.parseInt(args[i]);
            int shortestPath = getShortestPath(sourceVertex, goalVertex);
            String message = String.format("Shortest path from %d to %d is %d", sourceVertex, goalVertex, shortestPath);
            System.out.println(message);
        }
    }

    private static List<Vertex> getVerticesFromFile(String filename) throws IOException {
        List<Vertex> result = new ArrayList<Vertex>();
        Path path = Paths.get("/Users/matt/dev/courses/algorithms1/week5/test_cases/" + filename);
        BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, ", \t\n\r\f");
            Vertex v = new Vertex(Integer.parseInt(st.nextToken()));
            while (st.hasMoreTokens()) {
                int endVertex = Integer.parseInt(st.nextToken());
                int length = Integer.parseInt(st.nextToken());
                v.addEdge(new Edge(v.id, endVertex, length));
            }
            result.add(v);
        }
        return result;
    }

    private static int getShortestPath(int sourceVertex, int goalVertex) throws Exception {
        processedVertices = new boolean[vertices.size() + 1];
        shortestPathDistances = new int[vertices.size() + 1];

        processedVertices[sourceVertex] = true;
        shortestPathDistances[sourceVertex] = 0;

        while (!processedVertices[goalVertex]) {

            List<Edge> edgesToUnprocessedVertices = new ArrayList<Edge>();
            for (Vertex v : vertices) {
                if (processedVertices[v.id]) {
                    for (Edge e : v.edges) {
                        if (!processedVertices[e.endVertex]) {
                            edgesToUnprocessedVertices.add(e);
                        }
                    }
                }
            }

            Edge edgeWithShortestPath = null;
            int currentShortestPath = Integer.MAX_VALUE;
            for (Edge e : edgesToUnprocessedVertices) {
                int lengthToEdge = shortestPathDistances[e.startVertex];
                int edgePathLength = lengthToEdge + e.length;
                if (edgePathLength < currentShortestPath) {
                    edgeWithShortestPath = e;
                    currentShortestPath = edgePathLength;
                }
            }

            if (edgeWithShortestPath == null) { throw new Exception("Failed to find edge with shortest path"); }
            int newVertex = edgeWithShortestPath.endVertex;
            processedVertices[newVertex] = true;
            shortestPathDistances[newVertex] = currentShortestPath;

        }
        return shortestPathDistances[goalVertex];
    }
}
