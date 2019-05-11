package project;

import gridworld.actor.*;
import gridworld.grid.BoundedGrid;
import gridworld.grid.Location;
import gridworld.world.PacmanWorld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Main
{

    public static final int ROW = 27;

    public static final int COL = 24;

    public static int[][] map;

    public static final BoundedGrid grid = new BoundedGrid( ROW, COL );

    static
    {
        //just for safekeeping
        map = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
                        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, },
                        { 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, },
                        { 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, },
                        { 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, },
                        { 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, },
                        { 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, },
                        { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, },
                        { 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, },
                        { 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, },
                        { 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, },
                        { 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, },
                        { 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, },
                        { 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, },
                        { 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, },
                        { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, },
                        { 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, },
                        { 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, },
                        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, },
                        { 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, },
                        { 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, },
                        { 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, },
                        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, },
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } };


    }



    public static void main( String[] args )
    {
        PacmanWorld world = new PacmanWorld( grid );

        initGrid( grid );
        LevelPellet lp = new LevelPellet("level1");

        Pacman p = new Pacman();

        p.putSelfInGrid( grid, new Location(4,12) );

        world.show();


//        for(int[] row: map)
//        {
//            for(int col: row)
//            {
//                String str = "";
//                if(col == 1)
//                {
//                    str = "x";
//                }
//                else{
//                    str = "o";
//                }
//                System.out.print( str );
//            }
//            System.out.println(  );
//        }





    }


    private static void initGrid( BoundedGrid grid )
    {



        try
        {

            File level = new File(
                            "/Users/Shams/Documents/GitHub/PACMAN/Pacman/src/Levels/level0.txt" );
            String line;
            BufferedReader br = new BufferedReader( new FileReader( level ) );

            for ( int i = 0; i < Main.ROW - 1; i++ )
            {
                do
                {
                    //find next good line
                    line = br.readLine();
                    line = line.replaceAll( "[^a-z]",
                                    "" );

                } while ( line.length() <= 0 );
                System.out.println( "*" + line );
                for ( int j = 0; j < Main.COL ; j++ )

                {
                    if ( line.charAt( j ) == 'x' )
                    {
                        Wall w = new Wall();
                        w.putSelfInGrid( grid, new Location( i , j ) );
                        // Dont need i + 2 because the +2 in already incorppprated
                        // in the og map, we can change the og map and then  +2
                    }
                    else if ( line.charAt( j ) == 'o' )
                    {
                        Pellet p = new Pellet();
                        p.putSelfInGrid( grid, new Location( i , j ) );
                    }
                }
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
            System.err.println( "Brad says \"shet2\"" );
            System.exit( -1 );
        }


        //Pacman name
        String[] pacman = { "P", "A", "C", "M", "A", "N" };
        int i = 9;

        for ( String m : pacman )
        {
            TextCell t = new TextCell( m );
            t.putSelfInGrid( grid, new Location( 0, i ) );
            i++;
        }




        LevelPellet level1 = new LevelPellet( "level1" );
        level1.putSelfInGrid( grid, new Location( 20, 4 ) );

        LevelPellet level2 = new LevelPellet( "level2" );
        level2.putSelfInGrid( grid, new Location( 20, 12 ) );


        LevelPellet level3 = new LevelPellet( "level3" );
        level3.putSelfInGrid( grid, new Location( 20, 19 ) );



    }

}
