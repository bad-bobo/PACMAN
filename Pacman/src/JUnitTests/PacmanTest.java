package JUnitTests;

import gridworld.actor.*;
import gridworld.grid.BoundedGrid;
import gridworld.grid.Location;
import gridworld.world.PacmanWorld;
import org.junit.jupiter.api.Test;
import project.Main;
import project.Mechanics;

import static org.junit.jupiter.api.Assertions.*;


/**
 * This class tests the Pacman class.
 */
class PacmanTest
{

    /**
     * Tests the constructor for Pacman.
     */
    @Test void pacmanTest()
    {
        Pacman p = new Pacman();
        assertNotNull( p.getColor() );
    }


    /**
     * Tests the moveTo method and all its cases for Pacman, where Pacman moves one space in the current direction it is
     * facing.
     */
    @Test void moveTo()
    {
        Pacman p = new Pacman();
        BoundedGrid grid;
        assertThrows( IllegalStateException.class, () -> {

            p.moveTo( new Location( 2, 2 ) );
            ;

        } );
        grid = new BoundedGrid( Main.ROW, Main.COL );
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        p.putSelfInGrid( grid, new Location( 11, 1 ) );
        assertThrows( IllegalArgumentException.class, () -> {

            p.moveTo( new Location( 100, 100 ) );

        } );

        p.moveTo( new Location( 12, 1 ) );
        assertEquals( new Location( 12, 22 ), p.getLocation() );
        assertNull( grid.get( new Location( 12, 1 ) ) );

        p.moveTo( new Location( 11, 22 ) );
        assertEquals( new Location( 11, 22 ), p.getLocation() );

        Casper c = new Casper( 1 );
        c.putSelfInGrid( grid, new Location( 10, 22 ) );
        c.scare();
        p.moveTo( new Location( 10, 22 ) );
        assertNull( c.getGrid() );
        assertEquals( p.getLocation(), new Location( 11, 22 ) );

        Pellet pellet = new Pellet();
        pellet.putSelfInGrid( grid, new Location( 9, 22 ) );
        p.moveTo( new Location( 9, 22 ) );
        assertNull( pellet.getGrid() );
        assertEquals( new Location( 9, 22 ), p.getLocation() );

        Casper ca = new Casper( 1 );
        ca.putSelfInGrid( grid, new Location( 8, 22 ) );
        p.moveTo( new Location( 8, 22 ) );
        assertNotEquals( p.getLocation(), new Location( 8, 22 ) );
    }


    /**
     * Testing some missed tests,
     * 1) pacman goes to left pipe, should be transported to right pipe.
     */
    @Test void additionalMoveTo()
    {
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        Main.currentLevel = 1;
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        PacmanWorld world = new PacmanWorld( grid );

        Pacman pacman = new Pacman();
        pacman.putSelfInGrid( grid, new Location( 13, 22 ) );

        Pacman.newDirection = Location.NORTH;
        assertTrue( Pacman.newDirection == Location.NORTH );
        //        world.show();
        //        Mechanics.sleep( 1000 );

        pacman.moveTo( new Location( 12, 22 ) );
        assertEquals( pacman.getLocation(), new Location( 12, 1 ) );

    }


    /**
     * Tests the act method and all its cases for Pacman, where Pacman moves one space in the direction it is facing if
     * it is possible to do so.
     */
    @Test void act()
    {
        Pacman p = new Pacman();
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        p.putSelfInGrid( grid, new Location( 13, 1 ) );
        p.currentDirection = 270;
        p.act();
        assertEquals( p.getLocation(), new Location( 13, 2 ) );

        p.currentDirection = 180;
        p.act();
        assertNotEquals( new Location( 14, 1 ), p.getLocation() );
    }


    /**
     * Tests the canMove method and all its cases for Pacman, which checks if Pacman can move in the direction it is
     * currently facing (without breaking the rules of the game).
     */
    @Test void canMove()
    {
        Pacman pacman = new Pacman();

        Pacman.currentDirection = Location.NORTH;
        assertThrows( NullPointerException.class, () -> {

            pacman.canMove();

        } );

        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        PacmanWorld world = new PacmanWorld( grid );

        pacman.putSelfInGrid( grid, new Location( 20, 11 ) );
        Pineapple pine = new Pineapple();
        pine.putSelfInGrid( grid, new Location( 20, 10 ) );

//        world.show();
//        Mechanics.sleep( 3000);


        assertFalse( pacman.canMove() );

        Pacman.currentDirection = Location.EAST;
        assertTrue( pacman.canMove() );
    }


    /**
     * Tests the getDirection method for Pacman.
     */
    @Test void direction()
    {
        Pacman p = new Pacman();
        Pacman.newDirection = 0;
        assertEquals( 0, Pacman.newDirection );
        Pacman.newDirection = 90;
        assertEquals( 90, Pacman.newDirection );
    }


    /**
     * Tests the drawPacmanName for Pacman, which adds TextCell's to the top of the grid.
     */
    @Test void drawPacmanName()
    {
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        Pacman p = new Pacman();
        p.putSelfInGrid( grid, new Location( 3, 3 ) );
        p.drawPacmanName();

        assertTrue( ( Main.grid.get( new Location( 0, 9 ) ) ) instanceof TextCell );
        assertTrue( ( Main.grid.get( new Location( 0, 15 ) ) ) instanceof TextCell );

        assertEquals( p.toString(), p.toString() );
    }

}