public class Edge {
    private double weight;
    private GNode destVertex;

    public Edge(GNode dest, double weight) {
        this.destVertex = dest;
        this.weight = weight;
    }

    public Edge(GNode dest) {
        this.destVertex = dest;
        this.weight = 1;
    }

    public double getWeight() {
        return weight;
    }

    public GNode getDestVertex() {
        return destVertex;
    }
}
