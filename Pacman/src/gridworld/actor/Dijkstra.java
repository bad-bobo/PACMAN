package gridworld.actor;

import gridworld.grid.Location;
import org.junit.platform.commons.util.Preconditions;
import project.Main;
import project.Mechanics;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Dijkstra is a very smart Ghost. He uses Dijkstra's shortest path algorithm
 *  with the graph represented as an adjacency matrix.
 */
public class Dijkstra extends Ghost {

    private static final int NO_PARENT = -1;
    /**
     * The adjacency matrix
     */
    private static int[][] adjMatrix;
    /**
     * The map Level of this ghost.
     */
    private final int levelNumber;
    /**
     * the path of nodes leading upto a destination.
     */
    private ArrayList<Integer> path = new ArrayList<>();
    /**
     * The previous Actor
     */
    private Actor prevActor;
    /**
     * The next Actor
     */
    private Location prevLoc;


    /**
     * Creates a Pink Ghost with Dijkstra's algorithm
     *
     * @param levelNumber level number (for future updates)
     */
    public Dijkstra(int levelNumber) {
        super(Color.red);
        this.levelNumber = levelNumber;
        adjMatrix = Mechanics.loadFile("AdjMatrix_level" + levelNumber, 236,
                                       236, "");

    }

    /**
     * This  Algorithm is adapted from -=geeksforgeek.org (Linked down [1])=-
     *
     * @param adjacencyMatrix
     * @param startNode
     * @param destinationNode
     * @return TODO: Comment
     */
    private static ArrayList<Integer> dijkstra(int[][] adjacencyMatrix,
            int startNode, int destinationNode) {

        //Number of vertices
        int numberOfNodes = adjacencyMatrix.length;

        // shortestDistances[i] will hold the
        // shortest distance from src to i
        int[] shortestDistances = new int[numberOfNodes];

        // added[i] will true if vertex i is
        // included  in shortest path tree
        // or shortest distance from src to
        // i is finalized
        boolean[] added = new boolean[numberOfNodes];

        // Initialize all distances as
        // INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < numberOfNodes; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // Distance of source vertex from
        // itself is always 0
        shortestDistances[startNode] = 0;

        // Parent array to store shortest
        // path tree
        int[] parents = new int[numberOfNodes];

        // The starting vertex does not
        // have a parent
        parents[startNode] = NO_PARENT;

        // Find shortest path for all
        // vertices
        for (int i = 1; i < numberOfNodes; i++) {

            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            /*
             * Like basic selection sort, finds the smallest
             */
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < numberOfNodes;
                    vertexIndex++) {
                /* If not visited and distance is smaller than
                shortestDistance*/
                if ((added[vertexIndex] == false) &&
                        shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // Mark the picked vertex as
            // processed
            added[nearestVertex] = true;

            // Update dist value of the
            // adjacent vertices of the
            // picked vertex.
            for (int vertexIndex = 0; vertexIndex < numberOfNodes;
                    vertexIndex++) {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) <
                        shortestDistances[vertexIndex])) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] =
                            shortestDistance + edgeDistance;
                }
            }
        }

        ArrayList<Integer> arr = new ArrayList<>(destinationNode);
        createPath(destinationNode, parents, arr);
        //        System.out.println( "Dijkstra.dijkstrea: Path: " + arr );
        return arr;
    }

    // Function to print shortest path
    // from source to currentVertex
    // using parents array
    private static void createPath(int destinationVertex, int[] parents,
            List<Integer> arr) {

        // Base case : Source node has
        // been processed
        if (destinationVertex == NO_PARENT) {
            return;
        }
        createPath(parents[destinationVertex], parents, arr);
        //        System.out.print( destinationVertex + " " );
        arr.add(destinationVertex);


    }

    // Function that implements Dijkstra's
    // single source shortest path
    // algorithm for a graph represented
    // using adjacency matrix
    // representation

    @Override
    public void act() {
        if (isScared()) {
            Color yello=new Color(255,200,0);
            if (getColor()!=Color.blue)
                setColor( Color.blue );
            else setColor(yello);
            return;
        } else {
            setColor(Color.red);
        }
        if (Mechanics.convertToNode(Mechanics.getPacmanLocation(),
                                    levelNumber) <
                0) { //wander when pacman hiding
            System.out.println("Blinky: bruh where tf is Pacman");
            clearPath();
            path.clear();
            while (true) {
                int rand = (int) (Math.random() * (4));

                switch (rand) {
                    case 0:
                        if (canMove(new Location(location.getRow() + 1,
                                                 location.getCol()))) {
                            moveHelper(new Location(location.getRow() + 1,
                                                    location.getCol()));
                            return;
                        }
                        break;

                    case 1:
                        if (canMove(new Location(location.getRow() - 1,
                                                 location.getCol()))) {
                            moveHelper(new Location(location.getRow() - 1,
                                                    location.getCol()));
                            return;
                        }
                        break;
                    case 2:
                        if (canMove(new Location(location.getRow(),
                                                 location.getCol() + 1))) {
                            moveHelper(new Location(location.getRow(),
                                                    location.getCol() + 1));
                            return;
                        }
                        break;
                    case 3:
                        if (canMove(new Location(location.getRow(),
                                                 location.getCol() - 1))) {
                            moveHelper(new Location(location.getRow(),
                                                    location.getCol() - 1));
                            return;
                        }
                        break;

                }
            }

        }

        if (path.size() < 1) {
            path.clear();
            Location pacmanLoc = Mechanics.getPacmanLocation();
            if (pacmanLoc == null) {
                return;
            }
            int destNode = Mechanics.convertToNode(pacmanLoc, levelNumber);
            if (destNode < 0) {
                return;
            }

            int startNode = Mechanics.convertToNode(getLocation(), levelNumber);
            path = dijkstra(adjMatrix, startNode, destNode);
            path.remove(0);
            ArrayList<Point> pathOfPoints = new ArrayList<>();
            for (int node : path) {
                Location loc = Mechanics.convertToLocation(node, levelNumber);
                pathOfPoints.add(new Point(loc.getRow(), loc.getCol()));
            }
            visualizePath(pathOfPoints);
            System.out.println(pathOfPoints.size());
            System.out.println(pathOfPoints);

        }
        Location nextLoc = Mechanics.convertToLocation(path.remove(0),
                                                       levelNumber);
        moveHelper(nextLoc);
    }

public void clearPath(){
    ArrayList<Point> pathOfPoints = new ArrayList<>();
    for (int node : path) {
        Location loc = Mechanics.convertToLocation(node, levelNumber);
        pathOfPoints.add(new Point(loc.getRow(), loc.getCol()));
    }
    if (prevActor instanceof Pellet)prevActor.setColor(Color.yellow);
    super.clearPath(pathOfPoints);
}

    /**
     * moves and Repopulates the previous empty spaces or normal dot with a
     * mixture of pellets, powerpellet or Pineapple
     *
     * also prevents eating pineapples, powerpellets, and other ghosts.
     *
     * @param next
     */
    private void moveHelper(Location next) {
        Actor pa = this.prevActor;
        this.prevActor = (Actor) Main.grid.get(next);
        if (this.prevActor != null) {
            this.prevActor.removeSelfFromGrid();
        }
        moveTo(next);
        if (this.prevLoc != null && (pa == null || pa instanceof Pellet)) {
            Mechanics.repopulate().putSelfInGrid(Main.grid, this.prevLoc);
        } else if (this.prevLoc != null) {
            pa.putSelfInGrid(Main.grid, this.prevLoc);
        }
        this.prevLoc = next;

    }

    /**
     * Returns path of nodes
     *
     * @return
     */
    public ArrayList<Integer> getPath() {
        return path;
    }

    /*
     * Sources:
     * [1] https://www.geeksforgeeks.org/printing-paths-dijkstras-shortest-path-algorithm/
     *
     */
}
