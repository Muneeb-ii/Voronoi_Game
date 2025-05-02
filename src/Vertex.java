
import java.util.ArrayList;

/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: Represents a vertex in a graph.
 * 
 */

public class Vertex {
 
    private ArrayList<Vertex> adjacentVertices; // List of adjacent vertices
    private ArrayList<Edge> incidentEdges; // List of incident edges

    /**
     * Constructor to initialize a vertex with empty lists for adjacent vertices and incident edges.
     */
    public Vertex() {
        this.adjacentVertices = new ArrayList<Vertex>();
        this.incidentEdges = new ArrayList<Edge>();
    }
}
