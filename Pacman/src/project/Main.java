package project;

import gridworld.actor.LevelPellet;
import gridworld.actor.Pacman;
import gridworld.actor.TextCell;
import gridworld.grid.BoundedGrid;
import gridworld.grid.Location;
import gridworld.world.PacmanWorld;


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

        p.putSelfInGrid( grid, new Location(0,0) );

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




        //Pacman name
        String[] pacman = { "P", "A", "C", "M", "A", "N" };
        int i = 9;

        for ( String m : pacman )
        {
            TextCell t = new TextCell( m );
            t.putSelfInGrid( grid, new Location( 0, i ) );
            i++;
        }



        String levelselect = "select a level";

        TextCell t = new TextCell( "Level" );
        t.putSelfInGrid( grid, new Location( 3, 6 ) );
        TextCell y = new TextCell( "Select" );
        y.putSelfInGrid( grid, new Location( 3, 8 ) );

        LevelPellet level4 = new LevelPellet( "level1" );
        level4.putSelfInGrid( grid, new Location( 3, 7 ) );


//        LevelPellet level1 = new LevelPellet( "level1" );
//        level1.putSelfInGrid( grid, new Location( 5, 5 ) );
//        LevelPellet level2 = new LevelPellet( "level2" );
//        level2.putSelfInGrid( grid, new Location( 20, 10 ) );
//        LevelPellet level3 = new LevelPellet( "level3" );
//        level3.putSelfInGrid( grid, new Location( 15, 22 ) );

    }

}
