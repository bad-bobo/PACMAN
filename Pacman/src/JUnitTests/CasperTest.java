package JUnitTests;

import gridworld.actor.Casper;
import gridworld.actor.Ghost;
import gridworld.grid.BoundedGrid;
import gridworld.grid.Location;
import org.junit.jupiter.api.Test;
import project.Main;
import project.Mechanics;

import static org.junit.jupiter.api.Assertions.*;


class CasperTest
{
    private Casper casper = new Casper( 1 );


    /**
     * Testing is Scared for Casper
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


    @Test void moveTo()
    {
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ) );
        Casper casper = new Casper( 1 );
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
        assertNull( grid.get( new Location( 22, 22 ) ) );

        //Should throw  IllegalArgumentException b/c this location is not in the grid
        assertThrows( IllegalArgumentException.class, () -> {

            casper.moveTo( new Location( -1, -1 ) );

        }, "Location " + new Location( -1, -1 ) + " is not valid." );

        casper.moveTo( casper.getLocation() );

        //If newLocation == location, return
        assertEquals(new Location( 21, 22 ), casper.getLocation());

    }


    @Test void canMove()
    {
    }


    @Test void visualizePath()
    {
    }


    @Test void getScaredTimer()
    {
    }


    @Test void decScaredTimer()
    {
    }


    @Test void act()
    {
    }


    @Test void createNewPath()
    {
    }
}