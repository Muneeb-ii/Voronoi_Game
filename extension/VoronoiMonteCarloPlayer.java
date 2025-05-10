/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of this class: Create a Voronoi player using Monte Carlo simulation to evaluate the best vertex to place a token on.
 */

import java.util.*;
import java.util.stream.Collectors;

public class VoronoiMonteCarloPlayer extends VoronoiPlayerAlgorithm {
    // Number of turns in the game
    private static final int TOTAL_TURNS = 5;
    // Number of simulations to run for each candidate vertex
    private static final int SIMULATIONS_PER_CANDIDATE = 25;
    // Random number generator for shuffling and sampling
    private final Random rand = new Random();

    public VoronoiMonteCarloPlayer(VoronoiGraph g) {
        super(g);
    }

    @Override
    public Vertex chooseVertex(int playerIndex, int numRemainingTurns) {
        // Build sets of placed tokens so far to get the current state of the game
        Set<Vertex> myTokens = new HashSet<>();
        Set<Vertex> oppTokens = new HashSet<>();
        for (Vertex v : graph.getVertices()) {
            if (graph.hasToken(v)) {
                Integer owner = graph.getCurrentOwner(v);
                if (owner != null) {
                    if (owner == playerIndex){
                        myTokens.add(graph.getClosestToken(v));
                    }
                    else{
                        oppTokens.add(graph.getClosestToken(v));
                    }
                }
            }
        }

        // Compute list of truly available vertices
        List<Vertex> allVerts = new ArrayList<>(graph.getVertices());
        List<Vertex> available = allVerts.stream().filter(v -> !graph.hasToken(v)).collect(Collectors.toList()); // unclaimed vertices

        int movesDone = TOTAL_TURNS - numRemainingTurns +  // my past picks
                        (TOTAL_TURNS - numRemainingTurns); // opponent's past picks
        int movesLeft = 2*TOTAL_TURNS - movesDone;

        Vertex best = null;
        double bestScore = Double.NEGATIVE_INFINITY;

        // For each candidate v, run SIMULATIONS_PER_CANDIDATE random playouts
        for (Vertex candidate : available) {
            double sumDelta = 0;
            for (int sim = 0; sim < SIMULATIONS_PER_CANDIDATE; sim++) {
                // copy token sets
                Set<Vertex> simMy = new HashSet<>(myTokens);
                Set<Vertex> simOpp = new HashSet<>(oppTokens);
                // apply this turn's candidate
                simMy.add(candidate);

                // remaining vertices to pick from
                List<Vertex> pool = new ArrayList<>(available);
                pool.remove(candidate);
                Collections.shuffle(pool, rand);

                // simulate alternating picks from pool
                boolean myTurn = false;  // next pick is opponent
                for (int i = 0; i < movesLeft - 1; i++) {
                    Vertex pick = pool.get(i);
                    if (myTurn){
                        simMy.add(pick);
                    }
                    else{
                        simOpp.add(pick);
                    }
                    myTurn = !myTurn;
                }

                // score by Voronoi assignment
                int myScore = 0, oppScore = 0;
                for (Vertex u : allVerts) {
                    double bestMy = Double.POSITIVE_INFINITY;
                    for (Vertex t : simMy)
                        bestMy = Math.min(bestMy, graph.getDistance(u, t));
                    double bestOpp = Double.POSITIVE_INFINITY;
                    for (Vertex t : simOpp)
                        bestOpp = Math.min(bestOpp, graph.getDistance(u, t));

                    if (bestMy < bestOpp){
                        myScore += graph.getValue(u);
                    }
                    else{
                        oppScore += graph.getValue(u);
                    }
                }

                sumDelta += (myScore - oppScore);
            }

            // average score over all simulations
            double avgDelta = sumDelta / SIMULATIONS_PER_CANDIDATE;
            // if this candidate is better than the best so far, update best
            if (avgDelta > bestScore) {
                bestScore = avgDelta;
                best = candidate;
            }
        }

        return best;
    }
}