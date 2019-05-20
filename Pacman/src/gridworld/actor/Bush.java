package gridworld.actor;

import gridworld.grid.Grid;
import gridworld.grid.Location;
import project.Main;
import project.Mechanics;

import java.awt.*;


/**
 * TODO: Brad
 */
public class Bush extends Actor implements Edible
{

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
    public void action( Grid grid )
    {

        grid.clearGrid();
        Mechanics.initGrid( Mechanics.loadFile( "Map_level" + levelNumber, Main.ROW, Main.COL, "" ) );

        if ( levelNumber == 1 )
        {
            Main.currentLevel = 1;

            Pacman pacman = new Pacman();
            pacman.putSelfInGrid( grid, new Location( 20, 11 ) );

            Score score = new Score();
            score.putSelfInGrid( grid, new Location( 0, 0 ) );

            Casper capser = new Casper( 1 );
            capser.putSelfInGrid( grid, new Location( 22, 22 ) );
            Dijkstra dijkstra = new Dijkstra( 1 );
            dijkstra.putSelfInGrid( grid, new Location( 3, 2 ) );

            Pacman.drawPacmanName();
        }

    }



    @Override public void act()
    {

    }
}
