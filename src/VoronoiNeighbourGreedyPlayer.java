/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of this class: Create a Voronoi Neighbour Greedy algorithm for the Voronoi game.
 * 
 */

public class VoronoiNeighbourGreedyPlayer extends VoronoiPlayerAlgorithm{

    /**
     * Constructor for VoronoiNeighbourGreedyPlayer
     * 
     * @param g The VoronoiGraph to be used by this player.
     */
    public VoronoiNeighbourGreedyPlayer(VoronoiGraph g){
        super(g);
    }

    /**
     * Chooses the unclaimed vertex with the highest combined score, where the score for
     * a vertex v is defined as the sum of:
     *   1. The value of v itself.
     *   2. The values of all adjacent vertices u for which there is no token yet and
     *      the edge distance from v to u is at most 1.1.
     *
     * This heuristic favors vertices that not only have high individual value but also
     * are surrounded by valuable neighbors, aiming to maximize local control in the Voronoi game.
     *
     * @param playerIndex       the index of the current player 
     * @param numRemainingTurns the number of picks remaining for this player
     * @return the selected Vertex to place the token on
     */
    public Vertex chooseVertex(int playerIndex, int numRemainingTurns){
        Vertex out = null;
        int maxValue = -1;

        // Iterate through all vertices in the graph
        for(Vertex v : graph.getVertices()){
            // Check if the vertex does not have a token on it
            if(!graph.hasToken(v)){
                int totalValue = 0;
                // Iterate through all adjacent vertices of v
                for(Vertex neighbour : v.adjacentVertices()){
                    // Check if the neighbour does not have a token and is within distance 1.1
                    if(!graph.hasToken(neighbour) && v.getEdgeTo(neighbour).distance() <= 1.1){
                        totalValue += graph.getValue(neighbour);
                    }
                }
                // Add the value of the vertex itself
                totalValue += graph.getValue(v);
                // Check if the total value is greater than the current maxValue
                // If so, update maxValue and set out to the current vertex
                if(totalValue > maxValue){
                    maxValue = totalValue;
                    out = v;
                }
            }
        }
        return out;
    }
}
