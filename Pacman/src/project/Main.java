package project;

import gridworld.actor.*;
import gridworld.grid.BoundedGrid;
import gridworld.grid.Location;
import gridworld.world.PacmanWorld;


public class Main
{

    public static final int ROW = 27;

    public static final int COL = 24;

    //private static final String[][] map;


    public static final BoundedGrid grid = new BoundedGrid( ROW, COL );



    static
    {
        //X = Wall
        //N = None (empty)
        //O = Pellet

    }

    public static void main( String[] args )
    {
        Pacman pacman = new Pacman();
        PacmanWorld world = new PacmanWorld( grid );
        initGrid( grid );
//        Blinky blinky = new Blinky();
//        Bonnie bonnie = new Bonnie();
//        Clyde clyde = new Clyde();
        Score score = new Score();
        score.putSelfInGrid( grid, new Location( 1, 23 ) );
        //        blinky.putSelfInGrid( grid, new Location( 10, 15 ) );
        //        bonnie.putSelfInGrid( grid, new Location( 17, 16 ) );
        //        clyde.putSelfInGrid( grid, new Location( 17, 7 ) );

        pacman.putSelfInGrid( grid, new Location( 20, 11 ) );

        world.show();

    }


    public static void initGrid( BoundedGrid grid )
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
        String levelselect="select a level";
        //for (int j=0;j<levelselect.length();j+=2){
        TextCell t=new TextCell("Level");
        t.putSelfInGrid(grid,new Location(3,6));
        TextCell y=new TextCell("Select");
        y.putSelfInGrid(grid,new Location(4,6));

    //}

        LevelPellet level1=new LevelPellet(0);
        level1.putSelfInGrid(grid,new Location(5,5));



//        for ( int r = 0; r < ROW; r++ )
//        {
//            for ( int c = 0; c < COL; c++ )
//            {
//
//
//                //Walls
//                if ( map[r][c].contains( "X" ) )
//                {
//                    Wall w = new Wall();
//                    w.putSelfInGrid( grid, new Location( r, c ) );
//                }
//
//                if ( map[r][c].contains( "O" ) )
//                {
//                    Pellet p = new Pellet();
//                    p.putSelfInGrid( grid, new Location( r, c ) );
//                }
//
//            }
//        }

    }

}
