/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: Represents a graph
 *
 */

import java.util.ArrayList;

public class Graph {
    
    private ArrayList<Vertex> vertices; // List of vertices in the graph

    /**
     * Constructor to initialize a graph with a 0 vertices.
     */
    public Graph(){
        this(0);
    }

    /**
     * Constructor to initialize a graph with n vertices and a 0.0 probability of edge creation.
     * 
     * @param n the number of vertices in the graph
     */
    public Graph(int n){
        this(n, 0.0);
    }

    /**
     * Constructor to initialize a graph with n vertices and a specified probability of edge creation.
     * 
     * @param n           the number of vertices in the graph
     * @param probability the probability of creating an edge between any two vertices
     */
    public Graph(int n, double probability){
        vertices = new ArrayList<Vertex>();

        for(int i = 0; i < n; i++){
            vertices.add(new Vertex());
        }

        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(Math.random() < probability){
                    Edge e = new Edge(vertices.get(i), vertices.get(j), 1.0);
                    vertices.get(i).addEdge(e);
                    vertices.get(j).addEdge(e);
                }
            }
        }
    }
}
