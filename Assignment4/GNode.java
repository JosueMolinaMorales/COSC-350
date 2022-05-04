import java.util.LinkedList;
public class GNode implements Comparable<GNode>{
    private int data;
    private LinkedList<Edge> edges;
    private double distance;
    private GNode prevNode; //node that leads to this node

    public GNode(int data) {
        this.data = data;
        edges = new LinkedList<Edge> ();
        distance = 0;
        prevNode = null;
    }

    public void setPrevNode(GNode prevNode){
        this.prevNode = prevNode;
    }

    public GNode getPrevNode(){
        return prevNode;
    }

    public int getData() {
        return data;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public double getDistance(){
        return distance;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }

    @Override
    public int compareTo(GNode otherNode){
        if(this.distance > otherNode.distance){
            return 1;
        }
        if(this.distance < otherNode.distance){
            return -1;
        }
        return 1;

    }

    public double getWeightedEdge(GNode anotherNode){
        double weight = -1;
        for(Edge edge : edges){
            if(edge.getDestVertex() == anotherNode){
                weight = edge.getWeight();
            }
        }
        return weight;
    }

    public String toString(){
        return data + " " + distance;
    }
}
