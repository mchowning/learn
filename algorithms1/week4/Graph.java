package week4;

import java.util.Collections;
import java.util.TreeMap;

/**
 * Created by matt on 2/9/15.
 */

public class Graph extends TreeMap<Integer, Vertex> {

    public Graph() {
        super(Collections.reverseOrder());
    }

    public void addEdge(int tail, int head) {
        if (!containsKey(tail)) {
            put(tail, new Vertex(tail));
        }
        if (!containsKey(head)) {
            put(head, new Vertex(head));
        }
        get(tail).addOutgoingConnection(head);
        get(head).addIncomingConnection(tail);
    }

    public void setAllVerticesUnexplored() {
        for (Vertex v : values()) {
            v.setIsExplored(false);
        }
    }

    public void print() {
        for (Vertex v : values()) {
            v.print();
            System.out.println();
        }
    }
}
