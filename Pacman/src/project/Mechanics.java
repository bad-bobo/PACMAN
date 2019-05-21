package project;

import gridworld.actor.*;
import gridworld.grid.Location;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Holds useful methods
 */
public abstract class Mechanics
{
    public static Color[] colorValues = { Color.BLUE, Color.CYAN, Color.GREEN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK,
                    Color.RED };


    /**
     * pacman
     *
     * @param arr
     * @param row
     * @param col
     */
    public static void gridMessage(  int row, int col, String ... arr )
    {

        for ( String m : arr )
        {
            TextCell t = new TextCell( m );
            t.putSelfInGrid( Main.grid, new Location( row, col ) );
            col++;
        }

    }

    /**
     * Prints a 2d array with no spaces
     *
     * @param arr a 2d array of ints
     */
    public static void print2DArray( int[][] arr, String delim )
    {
        for ( int r = 0; r < arr.length; r++ )
        {
            for ( int c = 0; c < arr[0].length; c++ )
            {
                System.out.print( arr[r][c] + delim );
            }
            System.out.println();
        }
    }


    /**
     * Gets the currrent Location of Pacman
     *
     * @return
     */
    public static Location getPacmanLocation()
    {
        ArrayList<Location> actors = Main.grid.getOccupiedLocations();
        for ( Location loc : actors )
        {
            if ( Main.grid.get( loc ) instanceof Pacman )
            {
                return loc;
            }
        }

        return null;

    }


    public static void removeAll()
    {
        ArrayList<Location> actorLocs = Main.grid.getOccupiedLocations();
        for ( Location loc : actorLocs )
        {
            Actor a = (Actor)Main.grid.get( loc );

            if ( !( a instanceof Wall || a instanceof Pipe ) )
            {

                Main.grid.remove( loc );
            }
        }
    }


    /**
     * Converts the txt file into an 2d array of ints
     *
     * @param fileName the number of the level
     */
    public static int[][] loadFile( String fileName, int arrWidth, int arrHeight, String delim )
    {
        int[][] map = new int[arrWidth][arrHeight];
        Main m = new Main();
        InputStream in = m.getClass().getResourceAsStream( "/" + fileName + ".txt" );
        BufferedReader br = new BufferedReader( new InputStreamReader( in ) );

        for ( int r = 0; r < map.length; r++ )
        {
            String line = null;

            try
            {
                line = br.readLine();
            }
            catch ( IOException e )
            {
                e.printStackTrace();
                System.err.println( "***Brad says \"shet\"***" );
            }

            String[] nums = line.split( delim );
            int c = 0;
            for ( String num : nums )
            {

                num = num.trim();
                map[r][c] = Integer.parseInt( num );

                c++;
            }

        }

        return map;

    }


    /**
     * Loads a 2d array into the grid
     *
     * @param map
     */
    public static void initGrid( int[][] map )
    {
        for ( int r = 0; r < map.length; r++ )
        {
            for ( int c = 0; c < map[0].length; c++ )
            {

                if ( map[r][c] == 1 )
                {
                    Wall wall = new Wall();
                    wall.putSelfInGrid( Main.grid, new Location( r, c ) );
                }
                else if ( map[r][c] == 0 )
                {
                    Pellet p = new Pellet();

                    if ( Main.currentLevel == 0 )
                    {
                        p.setColor( colorValues[(int)( Math.random() * colorValues.length )] );
                    }
                    p.putSelfInGrid( Main.grid, new Location( r, c ) );
                }
                else if ( map[r][c] == 3 )
                {
                    Pipe p = new Pipe();
                    p.putSelfInGrid( Main.grid, new Location( r, c ) );

                }
                else if ( map[r][c] == 4 )
                {
                    Pineapple p = new Pineapple();
                    p.putSelfInGrid( Main.grid, new Location( r, c ) );

                }
                else if ( map[r][c] == 5 )
                {
                    PowerPellet p = new PowerPellet();
                    p.putSelfInGrid( Main.grid, new Location( r, c ) );

                }

            }
        }
    }

    public static Actor repopulate( Location loc )
    {
//        int r=loc.getRow();
//        int c=loc.getCol();
//        int[][] array=Mechanics.loadFile( "Map_level" + Main.currentLevel, Main.ROW, Main.COL, "" );
//        System.out.println(array[r][c]);
//                if ( array[r][c] == 0 )
//                {
//                    return new Pellet();
//                }
//                else if ( array[r][c] == 4 )
//                {
//                    return new Pineapple();
//                }
//        return new Wall();
double rand=Math.random();
if (rand>=.97)return new Pineapple();
if (rand>=.91) return new PowerPellet();
return new Pellet();
    }

    /**
     * Copies one array into another
     *
     * @param array
     * @return
     */
    public static int[][] copyArray( int[][] array )
    {
        int[][] newArray = new int[array.length][array[0].length];

        for ( int r = 0; r < array.length; r++ )
        {
            for ( int c = 0; c < array[0].length; c++ )
            {
                newArray[r][c] = array[r][c];
            }

        }
        return newArray;
    }


    /**
     * Converts a Location to a node number
     * We are given a location, like (0,0) then we have to see what node is in that location
     *
     * @param loc         the Location
     * @param levelNumber the level number
     * @return a node number
     */
    public static int convertToNode( Location loc, int levelNumber )
    {
        int[][] map = Mechanics.loadFile( "NodeMatrix_level" + levelNumber, Main.ROW, Main.COL, "\t" );

        int x = loc.getRow();
        int y = loc.getCol();
        if ( map[x][y] == -1 )
        {
            System.err.println( "Mechanics.convertToNode: Not a valid location" );
            return -1;

        }
        else
        {
            return map[x][y];
        }
    }


    /**
     * Converts a node number to a location
     *
     * @param node        a node number
     * @param levelNumber the level Number
     * @return a Location
     */
    public static Location convertToLocation( int node, int levelNumber )
    {
        int[][] map = Mechanics.loadFile( "NodeMatrix_level" + levelNumber, Main.ROW, Main.COL, "\t" );

        for ( int r = 0; r < map.length; r++ )
        {
            for ( int c = 0; c < map[0].length; c++ )
            {
                if ( map[r][c] == node )
                {
                    return new Location( r, c );
                }
            }
        }

        return null;

    }
}
