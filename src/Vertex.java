
import java.util.ArrayList;

/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: Represents a vertex in a graph.
 * 
 */

public class Vertex {
 
    private ArrayList<Edge> incidentEdges; // List of incident edges

    /**
     * Constructor to initialize a vertex with empty lists for adjacent vertices and incident edges.
     */
    public Vertex() {
        this.incidentEdges = new ArrayList<Edge>();
    }

    /**
     * Returns the edge connecting this vertex to the specified vertex.
     *
     * @param v the vertex to which to find the connecting edge
     * @return the edge connecting this vertex and v, or null if no such edge exists
     */
    public Edge getEdgeTo(Vertex v) {
        for (Edge e : incidentEdges) {
            Vertex other = e.other(this);
            if (other != null && other.equals(v)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Adds the specified edge to this vertex’s incident edges.
     *
     * @param e the edge to add
     */
    public void addEdge(Edge e) {
        incidentEdges.add(e);
    }

    /**
     * Removes an edge from the list of incident edges.
     * 
     * @param e the edge to be removed
     * @return true if the edge was successfully removed, false otherwise
     */
    public boolean removeEdge(Edge e){
        if (incidentEdges.contains(e)) {
            incidentEdges.remove(e);
            return true;
        }
        return false;
    }

    /**
     * Returns the list of incident edges.
     * 
     * @return the list of incident edges
     */
    public ArrayList<Edge> incidentEdges() {
        return incidentEdges;
    }

    /**
     * Returns the list of adjacent vertices.
     * 
     * @return the list of adjacent vertices
     */
    public ArrayList<Vertex> adjacentVertices() {
        ArrayList<Vertex> adjacentVertices = new ArrayList<Vertex>();
        for (Edge e : incidentEdges) {
            Vertex v = e.other(this);
            if (v != null) {
                adjacentVertices.add(v);
            }
        }
        return adjacentVertices;
    }
}
