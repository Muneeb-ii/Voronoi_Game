/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of this class: Create a better Voronoi Neighbour Greedy algorithm for the Voronoi game.
 * 
 */

 public class VoronoiNeighbourGreedyPlayer2 extends VoronoiPlayerAlgorithm{

    /**
     * Constructor for VoronoiNeighbourGreedyPlayer
     * 
     * @param g The VoronoiGraph to be used by this player.
     */
    public VoronoiNeighbourGreedyPlayer2(VoronoiGraph g){
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
        double maxValue = -1.0;

        // Iterate through all vertices in the graph
        for(Vertex v : graph.getVertices()){
            // Check if the vertex does not have a token on it
            if(!graph.hasToken(v)){
                double totalValue = 0.0;
                // Inverse-distance neighbor loop with opponent-aware filtering
                for (Vertex neighbour : v.adjacentVertices()) {
                    if (!graph.hasToken(neighbour)) {
                        double distYou = v.getEdgeTo(neighbour).distance();
                        Vertex oppTok = graph.getClosestToken(neighbour);
                        // Only count this neighbor if you can reach it at least as quickly as opponent
                        if (oppTok == null || graph.getDistance(oppTok, neighbour) >= distYou) {
                            totalValue += graph.getValue(neighbour) / graph.getDistance(v, neighbour);
                        }
                    }
                }
                // Add this vertex's value
                totalValue = totalValue + graph.getValue(v);

                if(totalValue > maxValue){
                    maxValue = totalValue;
                    out = v;
                }
            }
        }
        return out;
    }
}
