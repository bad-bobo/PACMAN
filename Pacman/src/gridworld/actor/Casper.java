package gridworld.actor;

import gridworld.grid.BoundedGrid;
import gridworld.grid.Grid;
import gridworld.grid.Location;
import project.Main;
import project.Mechanics;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Casper is a pink Ghost with Depth first search capabilities. This Ghost finds a path to pacman, but not the best path.
 */
public class Casper extends Ghost
{

    /**
     * The map of the game in 0'a and 1's
     */
    private static int[][] map = new int[Main.ROW][Main.COL];

    /**
     * The path of the Ghost in points.
     */
    private ArrayList<Point> path = new ArrayList<>();

    /**
     * The previous Location.
     */
    private Location prevLoc;

    /**
     * The previous Actor.
     */
    private Actor prevActor;


    /**
     * Creates a new Ghost, with DFS. Sets the color to blue.
     * Loads map from resource folder.
     * sets map at some locations to 1, because This ghost is restricted from going into the pipes.
     *
     * @param levelNumber the level number
     */
    public Casper( int levelNumber )
    {
        super( Color.CYAN );
        map = Mechanics.loadFile( "Map_level" + levelNumber, Main.ROW, Main.COL, "" );
        map[12][4] = 1;
        map[12][19] = 1;
    }


    /**
     * If the ghost is scared, scatters. Else finds a path to the Pacman and moves.
     */
    @Override public void act()
    {
        Grid<Actor> gr = getGrid();
        if ( gr == null )
        {
            return;
        }
        if ( isScared() )
        {
            Color yello = new Color( 255, 200, 0 );
            if ( getColor() != Color.blue )
            {
                setColor( Color.BLUE );
            }
            else
            {
                setColor( yello );
            }
            return;
        }
        else
        {
            setColor( Color.CYAN );
            Location pacmanLocation = Mechanics.getPacmanLocation( (BoundedGrid)gr );
//            System.out.println( pacmanLocation.getRow() );
            if ( pacmanLocation == null || ( ( pacmanLocation.getCol() < 5 || pacmanLocation.getCol() > 18 ) && pacmanLocation
                            .getRow() > 9 && pacmanLocation.getRow() < 15 ) )
            {
                clearPath( path );
                if ( prevActor instanceof Pellet )
                {
                    prevActor.setColor( Color.yellow );
                }

                path.clear();

                while ( true )
                {
                    //Coverage for this part cannot be  100% because it is random
                    int rand = (int)( Math.random() * ( 4 ) );

                    switch ( rand )
                    {
                        case 0:
                            if ( canMove( new Location( location.getRow() + 1, location.getCol() ) ) )
                            {
                                moveHelper( new Location( location.getRow() + 1, location.getCol() ) );
                                return;
                            }
                            break;

                        case 1:
                            if ( canMove( new Location( location.getRow() - 1, location.getCol() ) ) )
                            {
                                moveHelper( new Location( location.getRow() - 1, location.getCol() ) );
                                return;
                            }
                            break;
                        case 2:
                            if ( canMove( new Location( location.getRow(), location.getCol() + 1 ) ) )
                            {
                                moveHelper( new Location( location.getRow(), location.getCol() + 1 ) );
                                return;
                            }
                            break;
                        case 3:
                            if ( canMove( new Location( location.getRow(), location.getCol() - 1 ) ) )
                            {
                                moveHelper( new Location( location.getRow(), location.getCol() - 1 ) );
                                return;
                            }
                            break;

                    }
                }

            }
            createNewPath( pacmanLocation );
            Point p = path.remove( path.size() - 1 );
            Location next = new Location( p.getX(), p.getY() );
            moveHelper( next );
        }

    }


    /**
     * Clears the path
     */
    public void clearPath()
    {
        super.clearPath( path );
    }


    /**
     * moves and Repopulates the previous empty spaces or normal dot with a
     * mixture of pellets, powerpellet or Pineapple
     * <p>
     * also prevents eating pineapples, powerpellets, and other ghosts.
     *
     * @param next the next location
     */
    private void moveHelper( Location next )
    {
        Actor pa = this.prevActor;
        this.prevActor = (Actor)Main.grid.get( next );
        if ( this.prevActor != null )
        {
            this.prevActor.removeSelfFromGrid();
        }
        moveTo( next );
        if ( this.prevLoc != null && ( pa == null || pa instanceof Pellet ) )
        {
            Mechanics.repopulate().putSelfInGrid( Main.grid, this.prevLoc );
        }
        else if ( this.prevLoc != null )
        {
            pa.putSelfInGrid( Main.grid, this.prevLoc );
        }
        this.prevLoc = next;

    }


    /**
     * Creates a path to the destination. If the path arraylist already contains items, it doesnt create a new path.
     * Sets destination on map  to 9, then calls dfs, which finds a path to 9. Then sets map[destination] to 0.
     */
    private void createNewPath( Location destination )
    {
        map[destination.getRow()][destination.getCol()] = 9;
        int[][] mapCopy = Mechanics.copyArray( map );
        if ( path.size() < 1 )
        {
            path.clear();
            DFS( mapCopy, location.getRow(), location.getCol(), path );
            if ( path.size() == 1 )
                return;
            path.remove( path.size() - 1 );
            visualizePath( path, (BoundedGrid)getGrid() );
        }
        map[destination.getRow()][destination.getCol()] = 0;

    }


    /**
     * -=This Algorithm was adapted from leonardo-ono (linked down [1])=-
     * Uses Depth first search to find a path to Pacman.
     * 9 in the map is the destination
     * 2 in the map is a visited site
     * 1 in the map is a wall
     * 0 in the map is a open path.
     *
     * @param map  The 2d array
     * @param x    The current row of the Ghost
     * @param y    The current col of the Ghost
     * @param path A list of Points
     * @return a true if path found, false otherwise. But the path is changed
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


    /**
     * returns map
     *
     * @return The map
     */
    public static int[][] getMap()
    {
        return map;
    }


    /**
     * Returns path
     *
     * @return the map
     */
    public ArrayList<Point> getPath()
    {
        return path;
    }





    /*
     * Sources:
     * [1]https://github.com/leonardo-ono/JavaMazeSolverTest/blob/master/src/mazesolver/DepthFirst.java
     */
}
