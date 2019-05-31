package gridworld.actor;

import gridworld.grid.Grid;
import gridworld.grid.Location;
import project.Main;
import project.Mechanics;

import java.awt.*;


/**
 * A Bush is sort of a level selector, it is only there in level 0 and when the pacman eats it, the level materializes
 */
public class Bush extends Actor implements Food
{

    /**
     * The level number corresponding to the difficulty
     */
    private int levelNumber;


    /**
     * Creates a new Level Pellet with the specified map number
     *
     * @param levelNumber Level/map number
     */
    public Bush( int levelNumber )
    {

        super();
        setColor( new Color( 230, 255, 0 ) );

        Main.currentLevel = levelNumber;
        this.levelNumber = levelNumber;

    }


    /**
     * I think this is being called when the pacman eats this
     *
     * @param grid the grid
     */
    public void eatAction( Grid grid )
    {

        //Clears grid
        grid.clearGrid();

        //Loads new map, for now only 1 type of map (Map_level1"
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ) );

        Score score = new Score();
        score.putSelfInGrid( grid, new Location(1, 23) );

        //Puts pacman in the game
        Pacman pacman = new Pacman();
        pacman.putSelfInGrid( grid, new Location( 20, 11 ) );

        //Level 1, all Inkys (Easy)
        if ( levelNumber == 1 )
        {
            Main.currentLevel = 1;

            Inky inky1 = new Inky();
            inky1.putSelfInGrid( grid, new Location( 22, 22 ) );

            Inky inky2 = new Inky();
            inky2.putSelfInGrid( grid, new Location( 3, 2 ) );

            Inky inky3 = new Inky();
            inky3.putSelfInGrid( grid, new Location( 10, 10 ) );

            //            Dijkstra dijkstra = new Dijkstra(1);
            //            dijkstra.putSelfInGrid( grid, new Location( 22, 22 ) );

        }
        //Level 2, Capser and Dijkstra (Medium)
        if ( levelNumber == 2 )
        {
            Casper casper = new Casper( 1 );
            casper.putSelfInGrid( grid, new Location( 22, 22 ) );

            Dijkstra dijkstra = new Dijkstra( 1 );
            dijkstra.putSelfInGrid( grid, new Location( 3, 2 ) );

        }

        //Level 3, Casper, Dijkstra and Inky (Hard)
        if ( levelNumber == 3 )
        {
            Casper casper = new Casper( 1 );
            casper.putSelfInGrid( grid, new Location( 22, 22 ) );

            Dijkstra dijkstra = new Dijkstra( 1 );
            dijkstra.putSelfInGrid( grid, new Location( 3, 2 ) );

            Inky inky3 = new Inky();
            inky3.putSelfInGrid( grid, new Location( 10, 10 ) );

        }

        //Draws "pacman" ontop.
        Pacman.drawPacmanName();

    }


    /**
     * Stub, no  code in method
     */
    @Override public void act()
    {

    }
}
