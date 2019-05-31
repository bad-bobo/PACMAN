package project;
import gridworld.actor.*;
import gridworld.actor.Score;
import gridworld.actor.TextCell;
import gridworld.grid.BoundedGrid;
import gridworld.grid.Location;
import gridworld.world.PacmanWorld;


/**
 * Main Class to run everything
 */
public class Main
{

    /**
     * Number of rows in the grid
     */
    public static final int ROW = 27;

    /**
     * Number of columns in the grid
     */
    public static final int COL = 24;

    public static final BoundedGrid grid = new BoundedGrid( ROW, COL );

    public static int currentLevel;

    public static PacmanWorld world;


    /**
     * Main method
     * @param args string passed through command line
     */
    public static void main( String[] args )
    {
        world = new PacmanWorld( grid );
        loadGame();
    }


    /**
     * Loads the game level 0 (selector level)
     */
    public static void loadGame()
    {

        currentLevel = 0;
        Score.score = 0;
        Mechanics.initGrid( Mechanics.loadFile( "LevelSelector", ROW, COL, "" ) );

        TextCell one = new TextCell( "1" );
        one.putSelfInGrid( grid, new Location( 23,4 ) );

        TextCell two = new TextCell( "2" );
        two.putSelfInGrid( grid, new Location( 23,12 ) );

        TextCell three = new TextCell( "3" );
        three.putSelfInGrid( grid, new Location( 23,19 ) );


        //Left pellet
        Bush level1 = new Bush( 1 );
        level1.putSelfInGrid( grid, new Location( 20, 4 ) );

        //Middle pellet
        Bush level2 = new Bush( 2 );
        level2.putSelfInGrid( grid, new Location( 20, 12 ) );

        //Right Pellet
        Bush level3 = new Bush( 3 );
        level3.putSelfInGrid( grid, new Location( 20, 19) );

        Pacman p = new Pacman();
        p.putSelfInGrid( grid, new Location( 4, 12 ) );

//        String[] welcome = {"W", "E","L","C", "O", "M", "E", " ", "T", "O", " ", "P", "A", "C", "M", "A", "N", "!"};
//        Mechanics.gridMessage( welcome, 2, 3 );


        Mechanics.gridMessage(  4,3 , "W");
        Mechanics.gridMessage( 5,2, "A", "S", "D" );

        Mechanics.gridMessage(  4,20 , "↑");
        Mechanics.gridMessage( 5,19, "←", "↓", "→" );

        Pacman.drawPacmanName();
        world.show();
    }

}
