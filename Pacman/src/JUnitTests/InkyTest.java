package JUnitTests;

import gridworld.actor.Casper;
import gridworld.actor.Inky;
import gridworld.actor.Pacman;
import gridworld.grid.BoundedGrid;
import gridworld.grid.Location;
import gridworld.world.PacmanWorld;
import org.junit.jupiter.api.Test;
import project.Main;
import project.Mechanics;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * How do you test Inky?
 */
class InkyTest
{

    @Test void act()
    {
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        Main.currentLevel = 1;
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        PacmanWorld world = new PacmanWorld( grid );

        Inky inky = new Inky(  );
        inky.putSelfInGrid( grid, new Location( 22, 22 ) );
        Inky.clearScaredTimer();
        Inky.scare();

        inky.setColor( Color.BLACK );
        inky.act();
        inky.act();

        Casper.clearScaredTimer();

        inky.removeSelfFromGrid();
        inky.putSelfInGrid( grid, new Location( 22, 22 ) );
        Pacman pacman  = new Pacman();
        pacman.putSelfInGrid( grid, new Location( 12, 1 ) );

        assertEquals( inky.testCountOf1, 0 );
        //To test for randomness
        int countOf1 = 0;
        for ( int i = 0; i < 30; i++ )
        {
            inky.act();
        }


        assertTrue(inky.testCountOf1 < 20   );

        //Clear path stub test, nothing to do
        {
            inky.clearPath();
        }
    }






}