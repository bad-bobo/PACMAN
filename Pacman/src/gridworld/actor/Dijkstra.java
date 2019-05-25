package gridworld.actor;

import gridworld.grid.Location;
import project.Mechanics;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Dijkstra is a very smart Ghost. He uses Dijkstra's shortest path algorithm with the graph represented as an adjacency matrix.
 */
public class Dijkstra extends Ghost
{

    /**
     * The adjacency matrix
     */
    private static int[][] adjMatrix;

    /**
     * the path of nodes leading upto a destination.
     */
    private List<Integer> path = new ArrayList<>();

    /**
     * The map Level of this ghost.
     */
    private final int levelNumber;

    /**
     * The previous Actor
     */
    private Actor prevActor;

    /**
     * The next Actor
     */
    private Location prevLoc;

    private static final int NO_PARENT = -1;

    /**
     * Creates a Pink Ghost with Dijkstra's algorithm
     *
     * @param levelNumber level number (for future updates)
     */
    public Dijkstra( int levelNumber )
    {
        super( Color.GREEN );
        this.levelNumber = levelNumber;
        adjMatrix = Mechanics.loadFile( "AdjMatrix_level" + levelNumber, 236, 236, "" );

    }


    @Override public void act()
    {
        if ( isScared() )
        {
            setColor( Color.blue );
        }
        else
        {
            setColor( Color.green );
        }
        if ( path.size() < 1 )
        {
            path.clear();
            Location pacmanLoc = Mechanics.getPacmanLocation();
            if ( pacmanLoc == null )
            {
                return;
            }
            int destNode = Mechanics.convertToNode( pacmanLoc, levelNumber );
            if ( destNode < 0 )
            {
                return;
            }
            int startNode = Mechanics.convertToNode( getLocation(), levelNumber );
            path = dijkstra( adjMatrix, startNode, destNode );
            path.remove( 0 );
            ArrayList<Point> pathOfPoints = new ArrayList<>();
            for ( int node : path )
            {
                Location loc = Mechanics.convertToLocation( node, levelNumber );
                pathOfPoints.add( new Point( loc.getRow(), loc.getCol() ) );
            }
            visualizePath( pathOfPoints );
        }
        Location nextLoc = Mechanics.convertToLocation( path.remove( 0 ), levelNumber );
        repopulateHelper( nextLoc );
    }


    /**
     * Repopulates the previous cell if a mixture of pellets, powerpellet or Pineapple
     * @param next
     */
    public void repopulateHelper( Location next )
    {
        Actor pa = prevActor;
        prevActor = grid.get( next );
        if ( prevActor != null )
        {
            prevActor.removeSelfFromGrid();
        }
        moveTo( next );
        if ( prevLoc != null && ( pa == null || pa instanceof Pellet ) )
        {
            Mechanics.repopulate().putSelfInGrid( grid, prevLoc );
        }
        else if ( prevLoc != null )
        {
            pa.putSelfInGrid( grid, prevLoc );
        }
        prevLoc = next;

    }





    // Function that implements Dijkstra's
    // single source shortest path
    // algorithm for a graph represented
    // using adjacency matrix
    // representation
    //Todo: Shams: redo
    private static List<Integer> dijkstra(
                    int[][] adjacencyMatrix, int startVertex, int destinationVertex )
    {

        //Number of vertices
        int nVertices = adjacencyMatrix.length;

        // shortestDistances[i] will hold the
        // shortest distance from src to i
        int[] shortestDistances = new int[nVertices];

        // added[i] will true if vertex i is
        // included  in shortest path tree
        // or shortest distance from src to
        // i is finalized
        boolean[] added = new boolean[nVertices];

        // Initialize all distances as
        // INFINITE and added[] as false
        for ( int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++ )
        {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // Distance of source vertex from
        // itself is always 0
        shortestDistances[startVertex] = 0;

        // Parent array to store shortest
        // path tree
        int[] parents = new int[nVertices];

        // The starting vertex does not
        // have a parent
        parents[startVertex] = NO_PARENT;

        // Find shortest path for all
        // vertices
        for ( int i = 1; i < nVertices; i++ )
        {

            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for ( int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++ )
            {
                if ( !added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance )
                {
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
            for ( int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++ )
            {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if ( edgeDistance > 0 && ( ( shortestDistance + edgeDistance ) < shortestDistances[vertexIndex] ) )
                {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }

        List<Integer> arr = new ArrayList<>( destinationVertex );
        createPath( destinationVertex, parents, arr );
        //        System.out.println( "Dijkstra.dijkstrea: Path: " + arr );
        return arr;
    }


    // Function to print shortest path
    // from source to currentVertex
    // using parents array
    private static void createPath(
                    int destinationVertex, int[] parents, List<Integer> arr )
    {

        // Base case : Source node has
        // been processed
        if ( destinationVertex == NO_PARENT )
        {
            return;
        }
        createPath( parents[destinationVertex], parents, arr );
        //        System.out.print( destinationVertex + " " );
        arr.add( destinationVertex );

    }

}
