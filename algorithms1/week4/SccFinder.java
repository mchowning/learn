package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by matt on 2/9/15.
 */
public class SccFinder {

    private Graph graph = new Graph();
    private int globalFinishingTime;
    private Vertex dfsCallParent;

    public static void main(String[] args) throws IOException {
        List<List<Vertex>> sccList = new SccFinder().getAllSccsInFile(args[0]);
        List<Integer> sccSizes = getListSizes(sccList);
        Collections.sort(sccSizes, Collections.reverseOrder());
        printListSizes(sccSizes);
    }

    private static void printListSizes(List<Integer> sccSizes) {
        String output = "";
        for (int i = 0; i < Math.min(5, sccSizes.size()); i++) {
            if (!output.equals("")) {
                output += ",";
            }
            output += sccSizes.get(i);
        }
        System.out.print(output);
    }

    private static List<Integer> getListSizes(List<List<Vertex>> sccList) {
        List<Integer> sccSizes = new ArrayList<Integer>();
        for (List<Vertex> vs : sccList) {
            sccSizes.add(vs.size());
        }
        return sccSizes;
    }

    // Using Kosaraju's algorithm
    public List<List<Vertex>> getAllSccsInFile(String filename) throws IOException {
        generateGraphFromFile(filename);
        addReversedFinishingTimes();
        setParentVertices();
        return getSccs();
    }

    private List<List<Vertex>> getSccs() {
        Map<Integer, List<Vertex>> parentSCCMap = new HashMap<Integer, List<Vertex>>();
        for (Vertex v : graph.values()) {
            int parent = v.getParent();
            if (!parentSCCMap.containsKey(parent)) {
                parentSCCMap.put(parent, new ArrayList<Vertex>());
            }
            parentSCCMap.get(parent).add(v);
        }
        return new ArrayList<List<Vertex>>(parentSCCMap.values());
    }

    private void addReversedFinishingTimes() {
        doDfsLoop(true);
    }

    private void setParentVertices() {
        doDfsLoop(false);
    }

    private void doDfsLoop(boolean isReversed) {
        dfsCallParent = null;
        globalFinishingTime = 0;
        graph.setAllVerticesUnexplored();
        for (Vertex v : getVertexesSortedByDescendingFinishingTime(graph)) {
            if (!v.isExplored()) {
                dfsCallParent = v;
                Stack<Vertex> stack = new Stack<Vertex>();
                stack.add(v);
                while (!stack.isEmpty()) {
                    doDfsSubroutine(stack, isReversed);
                }
            }
        }
    }

    private void doDfsSubroutine(Stack<Vertex> stack, boolean isReversed) {
        if (stack.peek().isExplored()) {
            Vertex removedVertex = stack.pop();
            if (removedVertex.getFinishingTime() == Vertex.NONE) {
                globalFinishingTime++;
                removedVertex.setFinishingTime(globalFinishingTime);
            }
        } else {
            stack.peek().setParent(dfsCallParent.getId());
            stack.peek().setIsExplored(true);
            for (int vertexId : isReversed ? stack.peek().getIncomingConnections() : stack.peek().getOutgoingConnections()) {
                Vertex connectedVertex = graph.get(vertexId);
                if (!connectedVertex.isExplored()) {
                    stack.push(connectedVertex);
                }
            }
        }
    }

    private List<Vertex> getVertexesSortedByDescendingFinishingTime(Graph graph) {
        List<Vertex> vertexesSortedDescendingByFinishingTime = new ArrayList<Vertex>(graph.values());
        Collections.sort(vertexesSortedDescendingByFinishingTime, new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                return Integer.compare(v2.getFinishingTime(), v1.getFinishingTime());
            }
        });
        return vertexesSortedDescendingByFinishingTime;
    }

    private void generateGraphFromFile(String filename) throws IOException {
        Path path = Paths.get("/Users/matt/dev/courses/algorithms1/week4/test_cases/" + filename);
        BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            int tail = Integer.parseInt(st.nextToken());
            int head = Integer.parseInt(st.nextToken());
            graph.addEdge(tail, head);
        }
    }
}
