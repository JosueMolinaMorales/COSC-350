import java.util.*;

public class Graph {
    private HashSet <GNode> nodes;

    /**
     * Create A new Graph
     */
    public Graph() {
        nodes = new HashSet<GNode>();
    }

    /**
     * Add an edge to a graph 
     * @param vertex1 Node 1
     * @param vertex2 Node 2
     * @param weight Weight of the edges
     * @return return true if edge was created
     */
    public boolean addEdge(GNode vertex1, GNode vertex2, double weight) {
        return vertex1.getEdges().add(new Edge(vertex2, weight)) && vertex2.getEdges().add(new Edge(vertex1, weight));
    }

    /**
     * Add Vertex
     * @param vertex
     * @return
     */
    public boolean addVertex(GNode vertex) {
        return nodes.add(vertex);
    }

    /**
     * Print the graph
     */
    public void printGraph() {
        for(GNode v: nodes) {
            System.out.print("vertex name: " + v.getData() + ":\n");
            for(Edge e: v.getEdges()) {
                System.out.print("destVertex: " + e.getDestVertex().getData() + ", weight: " + e.getWeight() + "\n");
            }
            System.out.print("\n");
        }
    }

    /**
     * Dijkstra's shortest path. algorithm from the textbook.
     * @param start start node
     * @param end end node
     */
    public void dijkstraShortestPath(GNode start, GNode end){
        PriorityQueue<GNode> minQueue = new PriorityQueue<GNode>();// min queue to keep the smallest distance on top.

        //set all node distance to infinity, while adding them to queue
        for(GNode node : nodes){
            node.setDistance(Double.POSITIVE_INFINITY);
            node.setPrevNode(null);
        }
        start.setDistance(0);
        for(GNode node: nodes){
            minQueue.add(node);
        }

        // New implementation?
        GNode currNode;
        while (!minQueue.isEmpty()) {
            currNode = minQueue.peek();
            for (Edge edge : currNode.getEdges()) {
                if (minQueue.contains(edge.getDestVertex())) {
                    relax(currNode, edge.getDestVertex(), minQueue);
                }
            }
            minQueue.remove();
        }
        
        System.out.print("Shortest path from " + start.getData() + " to " + end.getData() +  " is ");

        LinkedList<GNode> pathFromStartToEnd = new LinkedList<>();
        GNode tempNode = end;
        while(tempNode != null){
            pathFromStartToEnd.addFirst(tempNode);
            tempNode = tempNode.getPrevNode();
        }

        for(int i = 0; i < pathFromStartToEnd.size(); i++){
            if(i < pathFromStartToEnd.size()-1)
                System.out.print(pathFromStartToEnd.get(i).getData() + " --> ");
            else
                System.out.print(pathFromStartToEnd.getLast().getData());
        }
        System.out.println("\nWith a total weight of : " + end.getDistance());
    }

    /**
     * Relax function used for Dijkstra's algorithm. Inspired from the textbook.
     * @param vertex1 vertex that leads to vertex2
     * @param vertex2
     */
    private void relax(GNode vertex1, GNode vertex2, PriorityQueue<GNode> pq){
        final double newWeight = vertex1.getDistance() + vertex1.getWeightedEdge(vertex2);
        if (newWeight < vertex2.getDistance()) {
            System.out.println(vertex2.getData() + " weight is now " + newWeight);
            System.out.println(vertex2.getData() + " previous node is now: " + vertex1.getData() + "\n");
            vertex2.setDistance(newWeight);
            vertex2.setPrevNode(vertex1);
            pq.remove(vertex2);
            pq.add(vertex2);
        }
    }

    /**
     * Gets the node from the graph
     * @param data the information being looked for
     * @return returns the node, or null if not found 
     */
    public GNode getNode(int data){
        for(GNode node : nodes){
            if(node.getData() == data){
                return node;
            }
        }
        return null;
    }
}
