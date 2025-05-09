/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: Represents a graph
 *
 */

import java.util.ArrayList;
import java.io.* ;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Graph {
    
    private ArrayList<Vertex> vertices; // List of vertices in the graph
    private ArrayList<Edge> edges; // List of edges in the graph

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
        edges = new ArrayList<Edge>();

        for(int i = 0; i < n; i++){
            vertices.add(new Vertex());
        }

        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(Math.random() < probability){
                    Edge e = new Edge(vertices.get(i), vertices.get(j), 1.0);
                    vertices.get(i).addEdge(e);
                    vertices.get(j).addEdge(e);
                    edges.add(e);
                }
            }
        }
    }

    /* 
     *
     * A graph constructor that takes in a filename and builds
     * the graph with the number of vertices and specific edges
     * specified.
    * 
     */
    public Graph( String filename ) {

        try {
            //Setup for reading the file
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            //Get the number of vertices from the file and initialize that number of vertices
            vertices = new ArrayList<Vertex>() ;
            Integer numVertices = Integer.valueOf( br.readLine().split( ": " )[ 1 ] ) ;
            for ( int i = 0 ; i < numVertices ; i ++ ) {
                vertices.add( new Vertex() );
            }

            //Read in the edges specified by the file and create them
            edges = new ArrayList<Edge>() ; //If you used a different data structure to store Edges, you'll need to update this line
            String header = br.readLine(); //We don't use the header, but have to read it to skip to the next line
            //Read in all the lines corresponding to edges
            String line = br.readLine();
                while(line != null){
                    //Parse out the index of the start and end vertices of the edge
                    String[] arr = line.split(",");
                    Integer start = Integer.valueOf( arr[ 0 ] ) ;
                    Integer end = Integer.valueOf( arr[ 1 ] ) ;

                    //Make the edge that starts at start and ends at end with weight 1
                    Edge edge = new Edge( vertices.get( start ) , vertices.get( end ) , 1. ) ;
                    //Add the edge to the set of edges for each of the vertices
                    vertices.get( start ).addEdge( edge ) ;
                vertices.get( end ).addEdge( edge ) ;
                //Add the edge to the ArrayList of edges in the graph
                this.edges.add( edge );

                //Read the next line
                line = br.readLine();
            }
            // call the close method of the BufferedReader:
            br.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Graph constructor:: unable to open file " + filename + ": file not found");
        }
        catch(IOException ex) {
            System.out.println("Graph constructor:: error reading file " + filename);
        } 
    }

    /**
     * Returns number of vertices in the graph.
     * 
     * @return the number of vertices in the graph
     */
    public int size(){
        return vertices.size();
    }

    /**
     * Returns the vertices in the graph.
     * 
     * @return the list of vertices in the graph
     */
    public ArrayList<Vertex> getVertices(){
        return vertices;
    }

    /**
     * Returns the list of edges in the graph.
     * 
     * @return the list of edges in the graph
     */
    public ArrayList<Edge> getEdges(){
        return edges;
    }

    /**
     * Adds a vertex to the graph.
     * 
     * @return the newly added vertex
     */
    public Vertex addVertex(){
        Vertex v = new Vertex();
        vertices.add(v);
        return v;
    }

    /**
     * Adds an edge between two vertices with a specified distance.
     * 
     * @param u        the first vertex
     * @param v        the second vertex
     * @param distance the distance between the two vertices
     * @return the newly added edge
     */
    public Edge addEdge(Vertex u, Vertex v, double distance){
        Edge e = new Edge(u, v, distance);
        u.addEdge(e);
        v.addEdge(e);
        edges.add(e);
        return e;
    }

    /**
     * Returns the edge between two vertices if it exists.
     * 
     * @param u the first vertex
     * @param v the second vertex
     * @return the edge between the two vertices, or null if no such edge exists
     */
    public Edge getEdge(Vertex u, Vertex v) {
        for (Edge e : edges) {
            if (e.vertices()[0].equals(u) && e.vertices()[1].equals(v) || e.vertices()[0].equals(v) && e.vertices()[1].equals(u)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Returns the vertex at the specified index.
     * 
     * @param index the index of the vertex
     * @return the vertex at the specified index
     */
    public Vertex getVertex(int index){
        return vertices.get(index);
    }

    /**
     * Removes a vertex from the graph.
     * 
     * @param vertex the vertex to be removed
     * @return true if the vertex was successfully removed, false otherwise
     */
    public boolean remove(Vertex vertex){
        if (!vertices.contains(vertex)) {
            return false;
        }
        // Remove the vertex from the incident edges of all its adjacent vertices
        vertices.remove(vertex);
        for (Edge e : edges) {
            if (e.vertices()[0].equals(vertex) || e.vertices()[1].equals(vertex)) {
                edges.remove(e);
            }
        }
        return true;
    }

    /**
     * Removes an edge from the graph.
     * 
     * @param edge the edge to be removed
     * @return true if the edge was successfully removed, false otherwise
     */
    public boolean remove(Edge edge){
        if (!edges.contains(edge)) {
            return false;
        }
        // Remove the edge from the incident edges of both vertices
        edge.vertices()[0].removeEdge(edge);
        edge.vertices()[1].removeEdge(edge);
        edges.remove(edge);
        return true;
    }

    /**
     * Calculates the shortest distance from a source vertex to all other vertices in the graph using Dijkstra's algorithm.
     * 
     * @param source the source vertex
     * @return a HashMap containing the shortest distances from the source vertex to all other vertices
     */
    public HashMap<Vertex, Double> distanceFrom(Vertex source){

        // Initialize the distance map
        HashMap<Vertex, Double> dist = new HashMap<>();
        // Set the distance to the source vertex to 0 and all other vertices to infinity
        for(Vertex v : vertices){
            if(v==source){
                dist.put(v, 0.0);
            }
            else{
                dist.put(v, Double.MAX_VALUE);
            }
        }

        // Create a priority queue to store vertices based on their distances
        Comparator<Vertex> vertexComparator = new Comparator<>(){
            @Override
            public int compare(Vertex v, Vertex u){
                return dist.get(v).compareTo(dist.get(u));
            }

        };

        // Create a priority queue to store vertices based on their distances
        PriorityQueue<Vertex> pq = new PriorityQueue<>(vertexComparator);

        // Add all vertices to the priority queue
        for(Vertex v: vertices){
            pq.add(v);
        }

        // While the priority queue is not empty, process the vertices
        while(!pq.isEmpty()){
            Vertex u =pq.poll();

            // For each incident edge of the vertex, update the distance to the other vertex
            for(Edge e : u.incidentEdges()){
                Vertex v = e.other(u);
                Double alt = dist.get(u) + e.distance();

                // If the new distance is less than the current distance, update it
                // and re-add the vertex to the priority queue
                if (alt<dist.get(v)){
                    dist.put(v, alt);
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }
        return dist;
    }
}
