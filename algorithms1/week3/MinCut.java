import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by matt on 2/6/15.
 */
@SuppressWarnings("WrongPackageStatement")
public class MinCut {

    private static Random random;

    public static void main(String[] args) throws IOException {
        random = new Random();
        Path path = Paths.get("/Users/matt/dev/courses/algorithms1/week3/" + args[0]);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        List<Edge> allEdges = readAllEdges(lines);
        System.out.println("" + getMinimumCut(10000, allEdges));
    }

    private static int getMinimumCut(int numRuns, List<Edge> allEdges) {
        int minCut = Integer.MAX_VALUE;
        for (int i = 0; i < numRuns; i++) {
            int cut = reduceToTwoVertices(allEdges).size();
            minCut = cut < minCut ? cut : minCut;
        }
        return minCut;
    }

    private static List<Edge> readAllEdges(List<String> lines) {
        List<Edge> allEdges = new ArrayList<Edge>();
        for (String aLine : lines) {
            List<Edge> lineEdges = readEdgesInLine(aLine);
            for (Edge edge : lineEdges) {
                if (!allEdges.contains(edge)) {
                    allEdges.add(edge);
                }
            }
        }
        return allEdges;
    }

    private static List<Edge> readEdgesInLine(String line) {
        String[] words = line.split("[\\t\\s]");
        List<Edge> lineEdges = new ArrayList<Edge>();
        int v1 = Integer.parseInt(words[0]);
        for (int i = 1; i < words.length; i++) {
            int v2 = Integer.parseInt(words[i]);
            lineEdges.add(new Edge(v1,v2));
        }
        return lineEdges;
    }

    private static List<Edge> reduceToTwoVertices(List<Edge> allEdges) {
        List<Edge> reducedEdges = deepCopyList(allEdges);
        while (numVertices(reducedEdges) > 2) {
            contractRandomEdge(reducedEdges);
        }
        return reducedEdges;
    }

    private static List<Edge> deepCopyList(List<Edge> allEdges) {
        List<Edge> reducedEdges = new ArrayList<Edge>();
        for (Edge e : allEdges) {
            reducedEdges.add(new Edge(e.vertex1, e.vertex2));
        }
        return reducedEdges;
    }

    private static int numVertices(List<Edge> allEdges) {
        HashSet<Integer> vertices = new HashSet<Integer>();
        for (Edge edge : allEdges) {
            vertices.add(edge.vertex1);
            vertices.add(edge.vertex2);
        }
        return vertices.size();
    }

    private static void contractRandomEdge(List<Edge> edges) {
        Edge removedEdge = edges.remove(random.nextInt(edges.size()));
        int remainingVertex = removedEdge.vertex1;
        int disappearingVertex = removedEdge.vertex2;
        updateAllVertices(edges, remainingVertex, disappearingVertex);
        removeSelfLoops(edges);
    }

    private static void removeSelfLoops(List<Edge> edges) {
        Iterator<Edge> iter = edges.iterator();
        while (iter.hasNext()) {
            Edge e = iter.next();
            if (e.vertex1 == e.vertex2) {
                iter.remove();
            }
        }
    }

    private static void updateAllVertices(List<Edge> edges, int remainingVertex, int disappearingVertex) {
        for (Edge edge : edges) {
            if (edge.vertex1 == disappearingVertex) {
                edge.vertex1 = remainingVertex;
            }
            if (edge.vertex2 == disappearingVertex) {
                edge.vertex2 = remainingVertex;
            }
        }
    }

    private static class Edge {
        public int vertex1;
        public int vertex2;
        private String name;

        public Edge(int vertex1, int vertex2) {
            assignVerticesInOrder(vertex1, vertex2);
            name = Integer.toString(vertex1) + "-" + Integer.toString(vertex2);
        }

        private void assignVerticesInOrder(int vertex1, int vertex2) {
            if (vertex1 < vertex2) {
                this.vertex1 = vertex1;
                this.vertex2 = vertex2;
            } else {
                this.vertex1 = vertex2;
                this.vertex2 = vertex1;
            }
        }

        public String getName() {
            return name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new Edge(vertex1, vertex2);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof  Edge)) return false;
            Edge other = (Edge) obj;
            return this.vertex1 == other.vertex1 &&
                   this.vertex2 == other.vertex2;
        }

        @Override
        public int hashCode() {
            int result = vertex1;
            result = 31 * result + vertex2;
            return result;
        }
    }
}
