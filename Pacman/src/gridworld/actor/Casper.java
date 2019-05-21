package gridworld.actor;

import gridworld.grid.Grid;
import gridworld.grid.Location;
import project.Main;
import project.Mechanics;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;


/**
 * Uses Depth First Search to find a path to Pacman, not the best path if p. Fixes the path and the endpoint, evem if pacman has moved from there.
 * TODO: error when two ghost get on the same path
 */
public class Casper extends Ghost
{

    private static int[][] map = new int[Main.ROW][Main.COL];

    private ArrayList<Point> path = new ArrayList<>();
    private Actor prevActor;
    private Location prevLoc;
    private static int scareTime=0;
    private static Color currentCol=Color.pink;

    /**
     * Creates a Pink Ghost
     */
    public Casper( int levelNumber )
    {
        super( Color.PINK );
        map = Mechanics.loadFile( "Map_level" + levelNumber, Main.ROW, Main.COL, "" );
        map[12][4] = 1;
        map[12][19] = 1;
//        Mechanics.print2DArray( map ,"");
    }

public static void scare(){
    scareTime=Ghost.SCARE_TIME;
    currentCol=Color.blue;
}
    /**
     * Just goes to the pacman, but doesnt find the shortest path
     */
    @Override public void act()
    {

        if (scareTime<0){currentCol=Color.pink;}
        else{
            scareTime--;
        }
        setColor(currentCol);
        Location pacmanLocation = Mechanics.getPacmanLocation();
        if ( pacmanLocation == null ||    map[pacmanLocation.getRow()][pacmanLocation.getCol()] == 2)
        {

            System.out.println( "Casper.act: No Pacman found" );
            return;
        }

        map[pacmanLocation.getRow()][pacmanLocation.getCol()] = 9;

Actor pa=prevActor;
Location pl=prevLoc;
Location next=DFS();
        prevActor=getGrid().get(next);
        prevLoc=next;
        if(prevActor!=null){
        prevActor.removeSelfFromGrid();}

        moveTo(next);
        Actor shouldBe=Mechanics.repopulate(pl);
        //if (pa==null||getGrid().getClass()!=shouldBe.getClass() ){
            //pa=shouldBe;
        //}
        shouldBe.putSelfInGrid(getGrid(),pl);
        map[pacmanLocation.getRow()][pacmanLocation.getCol()] = 0 ;

    }


    /**
     * Helper method
     *
     * @return
     */
    private Location DFS()
    {
        int[][] mapCopy = Mechanics.copyArray( map );

        if ( path.size() < 1 )
        {
            path.clear();
            DFS( mapCopy, location.getRow(), location.getCol(), path );

            //removes the last index, which is the current location of the Ghost
            path.remove( path.size() - 1 );
            int size = path.size();

            //shortens the path to 1/3, this will refresh the path
            //            for ( int i = 0; i < size / 3; i++ )
            //            {
            //                path.remove( 0 );
            //            }

            //Visualizing the path
            visualizePath( path );

        }

        Point p = path.remove( path.size() - 1 );

        return new Location( p.getX(), p.getY() );

    }


    /**
     * Uses Depth first search to find a path to Pacman.
     *
     * @param map  The 2d array
     * @param x    The current row of the Ghost
     * @param y    The current col of the Ghost
     * @param path A list of Points
     * @return
     */
    private boolean DFS(
                    int[][] map, int x, int y, List<Point> path )
    {
        if ( !grid.isValid( new Location( x, y ) ) )
        {
            return false;
        }
        //Target
        if ( map[x][y] == 9 )
        {
            path.add( new Point( x, y ) );
            return true;
        }

        //Changing point to point visited
        if ( map[x][y] == 0 )
        {

            map[x][y] = 2;

            //Searching one down
            if ( DFS( map, x + 1, y, path ) )
            {
                path.add( new Point( x, y ) );
                return true;
            }

            //Searching one left
            if ( DFS( map, x, y - 1, path ) )
            {
                path.add( new Point( x, y ) );
                return true;
            }

            //Searching one up
            if ( DFS( map, x - 1, y, path ) )
            {
                path.add( new Point( x, y ) );
                return true;
            }

            //Searching one right
            if ( DFS( map, x, y + 1, path ) )
            {
                path.add( new Point( x, y ) );
                return true;
            }

        }

        return false;

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
