package JUnitTests;

import gridworld.actor.*;
import gridworld.grid.BoundedGrid;
import gridworld.grid.Location;
import gridworld.world.PacmanWorld;
import org.junit.jupiter.api.Test;
import project.Main;
import project.Mechanics;

import static org.junit.jupiter.api.Assertions.*;


class CasperTest
{

    //==================================================================================================================
    //*******************************************|| GENERAL TESTS FOR GHOST CLASS ||************************************
    //==================================================================================================================


    /**
     * General ghost isScared test, not specific to Casper
     */
    @Test void isScared()
    {
        assertFalse( Casper.isScared() );
        Casper.scare();
        assertTrue( Casper.isScared() );
        for ( byte i = 0; i < 15; i++ )
        {
            //The mechanism that is in WorldFrame.repaint
            if ( Ghost.getScaredTimer() >= 0 )
            {
                Ghost.decScaredTimer();
            }
        }
        assertTrue( Casper.isScared() );
        for ( byte i = 0; i < 17; i++ )
        {
            //The mechanism that is in WorldFrame.repaint
            if ( Ghost.getScaredTimer() >= 0 )
            {
                Ghost.decScaredTimer();
            }
        }
        assertFalse( Casper.isScared() );
    }


    /**
     * General ghost scare test, not specific to Casper
     */
    @Test void scare()
    {
        assertFalse( Casper.isScared() );
        Casper.scare();
        assertTrue( Casper.isScared() );
        for ( byte i = 0; i < 31; i++ )
        {
            //The mechanism that is in WorldFrame.repaint
            if ( Ghost.getScaredTimer() >= 0 )
            {
                Ghost.decScaredTimer();
            }
        }
        assertFalse( Casper.isScared() );

    }


    /**
     * General test for Ghost moveTo, not specific to Casper.
     */
    @Test void moveTo()
    {
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        Main.currentLevel = 1;
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        PacmanWorld world = new PacmanWorld( grid );
        Casper casper = new Casper( 1 );
        Pacman pacman = new Pacman();
        BoundedGrid nullGrid = null;

        //Should throw a nullPointer Exception b/c the grid is null
        assertThrows( NullPointerException.class, () -> {

            casper.putSelfInGrid( nullGrid, new Location( 22, 22 ) );

        } );

        casper.putSelfInGrid( grid, new Location( 22, 22 ) );
        assertEquals( new Location( 22, 22 ), casper.getLocation() );

        casper.moveTo( new Location( 21, 22 ) );
        assertEquals( new Location( 21, 22 ), casper.getLocation() );

        //Should be null because casper has moved from this location.
        assertTrue( grid.get( new Location( 22, 22 ) ) instanceof Pellet );

        //Should throw  IllegalArgumentException b/c this location is not in the grid
        assertThrows( IllegalArgumentException.class, () -> {

            casper.moveTo( new Location( -1, -1 ) );

        }, "Location " + new Location( -1, -1 ) + " is not valid." );

        casper.moveTo( casper.getLocation() );

        //If newLocation == location, return
        assertEquals( new Location( 21, 22 ), casper.getLocation() );

        //Testing that Ghost doesn't eat pellets
        grid.clearGrid();
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        casper.putSelfInGrid( grid, new Location( 22, 22 ) );
        casper.moveTo( new Location( 21, 22 ) );
        casper.moveTo( new Location( 20, 22 ) );
        assertTrue( grid.get( new Location( 21, 22 ) ) instanceof Pellet );

        //Testing that Ghost doesnt eat PowerPellets. The pellet is moved one down
        grid.clearGrid();
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        casper.putSelfInGrid( grid, new Location( 22, 22 ) );
        PowerPellet pp = new PowerPellet();
        grid.remove( new Location( 21, 22 ) );
        pp.putSelfInGrid( grid, new Location( 21, 22 ) );
        casper.moveTo( new Location( 21, 22 ) );
        casper.moveTo( new Location( 20, 22 ) );
        assertTrue( grid.get( new Location( 22, 22 ) ) instanceof PowerPellet );

    }


    /**
     * General test for Ghost.canMove(), not specific to Casper.
     */
    @Test void canMove()
    {
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        Main.currentLevel = 1;
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        PacmanWorld world = new PacmanWorld( grid );
        Casper casper = new Casper( 1 );

        casper.putSelfInGrid( grid, new Location( 22, 22 ) );

        // A Ghost cannot move into the pipe entrances
        {
            assertFalse( casper.canMove( new Location( 12, 4 ) ) );
            assertFalse( casper.canMove( new Location( 12, 19 ) ) );
        }

        //A Ghost cannot move if the grid is null
        {
            /* This case will never happen because you cannot put the Ghost in the grid if the grid is null */
        }

        //A Ghost cannot move to a location that is out of bounds
        {
            assertFalse( casper.canMove( new Location( -1, -1 ) ) );
        }

        //A Ghost cannot move backwards
        {

            casper.moveTo( new Location( 21, 22 ) );
            assertFalse( casper.canMove( new Location( 22, 22 ) ) );
        }

        //A Ghost cannot move into a wall
        {
            assertFalse( casper.canMove( new Location( 2, 0 ) ) );
        }

        assertTrue( casper.canMove( new Location( 20, 22 ) ) );

    }


    /**
     * General test for the Ghost scared peripherals
     */
    @Test void timer()
    {
        Ghost.clearScaredTimer();
        assertEquals( -1, Ghost.getScaredTimer() );
        Ghost.scare();
        assertEquals( 30, Ghost.getScaredTimer() );
        Ghost.decScaredTimer();
        assertEquals( 29, Ghost.getScaredTimer() );

    }


    //==================================================================================================================
    //******************************************|| SPECIFIC TESTS FOR CASPER CLASS ||***********************************
    //==================================================================================================================
    @Test void visualizePath()
    {
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        Main.currentLevel = 1;
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        PacmanWorld world = new PacmanWorld( grid );

        Casper casper = new Casper( 1 );
        casper.putSelfInGrid( grid, new Location( 22, 22 ) );

        //Path should be null b/c no path is made
        assertNull( casper.getPath() );
        casper.act();


    }


    /**
     * Specific to Casper, tests its act method
     */
    @Test void act()
    {

    }

}