# Voronoi_Game

## Project Overview

This project implements and evaluates multiple strategies for the two-player Voronoi game on graphs. Players alternately place tokens on vertices of a weighted graph (100 vertices, edge-probability 10%, edge-weights in [1,2], vertex-values in [0,100]). After all turns (k = 10 each), each vertex is assigned to the nearest token and players score the sum of their owned values.

## Core Features

- **Graph Model**  
  - `Vertex`, `Edge`, and `Graph` classes support undirected graphs with weighted edges.  
  - Dijkstra’s algorithm (`distanceFrom`) computes all‐pairs shortest distances.

- **Player Algorithms**  
  - **Random** (`VoronoiRandomPlayer`): picks an unclaimed vertex uniformly at random.  
  - **Greedy** (`VoronoiGreedyPlayer`): picks the highest‐value unclaimed vertex.  
  - **Neighborhood-Greedy** (`VoronoiNeighbourGreedyPlayer[2]`): scores each candidate by its own value plus inverse-distance-weighted neighbors you can still capture.  
  - **Monte Carlo** (`VoronoiMonteCarloPlayer`): for each candidate, runs a fixed number of random playouts to estimate expected net gain and picks the best.

- **Visualization & Simulation**  
  - `VoronoiGame.java` provides an interactive display on a small demo graph (click to advance).  
  - After the demo, it runs 20 (or user-configured) games: 10 with Player 1 going first, 10 with Player 2 going first—and reports win/tie counts.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher  
- Git (optional)

### Compilation

```bash
git clone https://github.com/Muneeb-ii/Voronoi_Game.git
cd Voronoi_Game/extension
javac *.java
```

### Running the Game

```
# From the extension directory:

java VoronoiGame
```

When prompted, enter the two-player class names (e.g., VoronoiNeighbourGreedyPlayer2 and VoronoiMonteCarloPlayer). To adjust the total simulations (default 20 games), edit the games variable in VoronoiGame.java - half the desired total (e.g. 500 for 1,000 games).

## Code Organization
```
.
└── src/
    ├── Graph.java                  # Base Graph with Vertex list, Edge list, Dijkstra
    ├── Vertex.java                 # Vertex with incident edges and adjacency
    ├── Edge.java                   # Edge with endpoints and distance
    ├── VoronoiGraph.java           # Subclass: token placement, Voronoi assignment
    ├── VoronoiGame.java            # Main: visualization & batch simulation
    ├── VoronoiRandomPlayer.java
    ├── VoronoiGreedyPlayer.java
    ├── VoronoiNeighbourGreedyPlayer.java
└── extension/
    ├── VoronoiNeighbourGreedyPlayer2.java
    └── VoronoiMonteCarloPlayer.java
```

## Extensions
- You can create additional VoronoiPlayerAlgorithm subclasses and compare them using the same framework.
- Tweak the graph generation parameters in VoronoiGraph(int n, double density) to test strategies on denser or sparser networks.

## Acknowledgments
- Lab specification and starter code by CS231 instructors at Colby College.
- Java’s PriorityQueue and Dijkstra pseudocode from Wikipedia.
- Inspiration for Monte Carlo playouts from [Monte-Carlo-Simulation_BlackJack](https://github.com/Muneeb-ii/Monte-Carlo-Simulation_Blackjack)

## License
This project is released under the MIT License.

