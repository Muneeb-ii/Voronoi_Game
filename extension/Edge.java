/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: Represents an edge in a graph.
 * 
 */

public class Edge {

    private Vertex v; // The other vertex of the edge
    private Vertex u; // The vertex of the edge
    private double distance; // The distance between the two vertices

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
        this.distance = distance;
    }

    /**
     * Returns the distance between the two vertices of the edge.
     * 
     * @return the distance between the two vertices
     */
    public double distance(){
        return distance;
    }

    /**
     * Returns the other vertex of the edge if it exists.
     * 
     * @param x the vertex to check
     * @return the other vertex of the edge if x is one of the vertices, null otherwise
     */
    public Vertex other(Vertex x){
        if (x == u) {
            return v;
        } 
        else if (x == v) {
            return u;
        } 
        else {
            return null;
        }
    }

    /**
     * Returns the vertices of the edge.
     * 
     * @return an array containing the two vertices of the edge
     */
    public Vertex[] vertices(){
        return new Vertex[]{u,v};
    }
}
