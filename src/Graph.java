/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: Represents a graph
 *
 */

import java.util.ArrayList;
import java.io.* ;

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
}
