package gridworld.actor;

import gridworld.grid.Grid;
import gridworld.grid.Location;
import project.Main;
import project.Mechanics;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Dijkstra extends Ghost
{

    private static int[][] adjMatrix;

    private static int[][] map = new int[Main.ROW][Main.COL];

    List<Integer> path = new ArrayList<>();

    private int levelNumber;
    private Actor prevActor;
    private Location prevLoc;
    private static int scareTime=0;
    private static Color currentCol=Color.green;

    /**
     * Creates a Pink Ghost with Dijkstra's greedy algorithm
     */
    public Dijkstra( int levelNumber )
    {
//        super( new Color( 57, 255, 108 ) );
        super(Color.GREEN);
        this.levelNumber = levelNumber;
        map = Mechanics.loadFile( "Map_level" + levelNumber, Main.ROW, Main.COL, "" );

        if ( levelNumber == 1 )
        {
            //236 nodes
            adjMatrix = Mechanics.loadFile( "AdjMatrix_level" + levelNumber, 236, 236, "" );

        }

    }
    public static void scare(){
        scareTime=Ghost.SCARE_TIME;
        currentCol=Color.blue;
    }

    @Override public void act()
    {
        if (scareTime<0){currentCol=Color.green;}
        else{
            scareTime--;
        }
        setColor(currentCol);

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
//
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
        Actor pa=prevActor;
        Location pl=prevLoc;
        prevActor=getGrid().get(nextLoc);
        prevLoc=nextLoc;
        if(prevActor!=null){
            prevActor.removeSelfFromGrid();}

        moveTo(nextLoc);
        Actor shouldBe=Mechanics.repopulate(pl);
        //if (pa==null||getGrid().getClass()!=shouldBe.getClass() ){
        //pa=shouldBe;
        //}
        shouldBe.putSelfInGrid(getGrid(),pl);

    }


    private static final int NO_PARENT = -1;


    // Function that implements Dijkstra's
    // single source shortest path
    // algorithm for a graph represented
    // using adjacency matrix
    // representation
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
    public void putSelfInGrid( Grid<Actor> gr, Location loc )
    {
        if ( grid != null )
            throw new IllegalStateException( "This actor is already contained in a grid." );
        prevActor =gr.get(loc);
        prevLoc=loc;
        if (prevActor!=null)
        prevActor.removeSelfFromGrid();
        Actor actor = gr.get( loc );
        if ( actor != null )
            actor.removeSelfFromGrid();
        gr.put( loc, this );
        grid = gr;
        location = loc;
    }

}
