/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: Represents an edge in a graph.
 * 
 */

public class Edge {

    private Vertex v; // The other vertex of the edge
    private Vertex u; // The vertex of the edge
    private int distance; // The length of the edge

    /**
     * Constructor to initialize an edge with two vertices and a distance.
     * 
     * @param u the vertex of the edge
     * @param v the other vertex of the edge
     * @param distance the distance between the two vertices
     */
    public Edge(Vertex u, Vertex v, double distance){
        this.u = u;
        this.v = v;
        this.distance = (int) distance;
    }
}
