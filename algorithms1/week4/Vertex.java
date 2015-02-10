package week4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 2/9/15.
 */
public class Vertex {

    public static final int NONE = -1;

    private int id;
    private int finishingTime = NONE;
    private int parent = NONE;
    private boolean isExplored = false;
    private List<Integer> outgoingConnections = new ArrayList<Integer>();
    private List<Integer> incomingConnections = new ArrayList<Integer>();

    public Vertex (int id) {
        this.id = id;
    }

    public List<Integer> getOutgoingConnections() {
        return outgoingConnections;
    }

    public void addOutgoingConnection(int c) {
        outgoingConnections.add(c);
    }

    public List<Integer> getIncomingConnections() {
        return incomingConnections;
    }

    public void addIncomingConnection(int c) {
        incomingConnections.add(c);
    }

    public int getId() {
        return id;
    }

    public boolean isExplored() {
        return isExplored;
    }

    public void setIsExplored(boolean isExplored) {
        this.isExplored = isExplored;
    }

    public int getFinishingTime() {
        return finishingTime;
    }

    public void setFinishingTime(int finishingTime) {
        this.finishingTime = finishingTime;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "id:" + id + ", ft:" + finishingTime + ", p:" + parent;
    }

    public void print() {
        System.out.println("id = " + id);
        System.out.println("finishing time = " + finishingTime);
        System.out.println("parent = " + parent);
        String connectionsStr = "";
        for (int conn : outgoingConnections) {
            if (!connectionsStr.isEmpty()) {
                connectionsStr += ", ";
            }
            connectionsStr += conn;
        }
        System.out.println("outgoingConnections = " + connectionsStr);
    }
}
