package JUnitTests;

import gridworld.actor.Casper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import gridworld.actor.*;
import gridworld.grid.*;
import project.Main;
import project.Mechanics;

class PacmanTest {

    /**
     * Tests the constructor for Pacman.
     */
    @Test
    void Pacman() {
        Pacman p = new Pacman();
        assertNotNull(p.getColor());
    }

    /**
     * Tests the moveTo method and all its cases for Pacman, where Pacman moves one space in the current direction it is
     * facing.
     */
    @Test
    void moveTo() {
        Pacman p = new Pacman();
        BoundedGrid grid;
        assertThrows( IllegalStateException.class, () -> {

            p.moveTo(new Location(2,2));;

        } );
        grid = new BoundedGrid( Main.ROW, Main.COL );
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        p.putSelfInGrid(grid, new Location(11, 1));
        assertThrows( IllegalArgumentException.class, () -> {

            p.moveTo(new Location(100, 100));

        } );

        p.moveTo(new Location(12, 1));
        assertEquals(new Location(12, 22), p.getLocation());
        assertNull(grid.get(new Location (12, 1)));

        p.moveTo(new Location(11, 22));
        assertEquals(new Location(11, 22), p.getLocation());

        Casper c = new Casper(1);
        c.putSelfInGrid(grid, new Location (10, 22));
        c.scare();
        p.moveTo(new Location(10, 22));
        assertNull(c.getGrid());
        assertEquals(p.getLocation(), new Location(11, 22));

        Pellet pellet = new Pellet();
        pellet.putSelfInGrid(grid, new Location(9, 22));
        p.moveTo(new Location(9, 22));
        assertNull(pellet.getGrid());
        assertEquals(new Location(9, 22), p.getLocation());

        Casper ca = new Casper(1);
        ca.putSelfInGrid(grid, new Location(8, 22));
        p.moveTo(new Location (8, 22));
        assertNotEquals(p.getLocation(), new Location(8, 22));
    }

    /**
     * Tests the act method and all its cases for Pacman, where Pacman moves one space in the direction it is facing if
     * it is possible to do so.
     */
    @Test
    void act() {
        Pacman p = new Pacman();
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        p.putSelfInGrid(grid, new Location(14, 1));
        p.setDirection(270);
        p.act();
        assertEquals(p.getLocation(), new Location(13, 1));

        p.setDirection(180);
        p.act();
        assertNotEquals(new Location(14, 1), p.getLocation());
    }

    /**
     * Tests the canMove method and all its cases for Pacman, which checks if Pacman can move in the direction it is
     * currently facing (without breaking the rules of the game).
     */
    @Test
    void canMove() {
        Pacman p = new Pacman();
        assertThrows( NullPointerException.class, () -> {

            p.canMove();

        } );

        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        p.putSelfInGrid(grid, new Location(14, 1));
        Pineapple pine = new Pineapple();
        pine.putSelfInGrid(grid, new Location(14, 2));
        p.setDirection(0);
        assertTrue(p.canMove());

        Wall w = new Wall();
        w.putSelfInGrid(grid, new Location(13, 1));
        p.setDirection(90);
        assertFalse(p.canMove());
    }

    /**
     * Tests the getDirection method for Pacman.
     */
    @Test
    void getDirection() {
        Pacman p = new Pacman();
        assertEquals(0, p.getDirection());
        Pacman.currentDirection = 90;
        assertEquals(90, p.getDirection());
    }

    /**
     * Tests the drawPacmanName for Pacman, which adds TextCell's to the top of the grid.
     */
    @Test
    void drawPacmanName() {
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        Pacman p = new Pacman();
        p.putSelfInGrid(grid, new Location(3, 3));
        p.drawPacmanName();

        assertTrue( (Main.grid.get(new Location(0, 9))) instanceof TextCell);
        assertTrue( (Main.grid.get(new Location(0, 15))) instanceof TextCell);
    }
}