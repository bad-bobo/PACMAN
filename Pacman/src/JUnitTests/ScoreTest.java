package JUnitTests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import gridworld.actor.*;
import gridworld.grid.*;
import project.Main;

/**
 * This class tests the Score class.
 */
class ScoreTest {

    /**
     * Tests the score constructor.
     */
    @Test
    void Score()
    {
        Score s = new Score();
        assertNotNull(s);
    }

    /**
     * Tests the inc method for score.
     */
    @Test
    void inc() {
        Score s = new Score();
        s.inc();
        assertEquals(1, s.score);
    }

    /**
     * Tests the clear method for score.
     */
    @Test
    void clear() {
        Score s = new Score();
        s.inc();
        s.inc();
        s.clear();
        assertEquals(0, s.score);
    }

    /**
     * Tests the second inc method for score with an int parameter.
     */
    @Test
    void inc1() {
        Score s = new Score();
        s.clear();
        s.inc(5);
        assertEquals(5, s.score);
    }

    /**
     * Tests the act method for score.
     */
    @Test
    void act() {
        Score s = new Score();
        s.putSelfInGrid(Main.grid, new Location(3, 3));
        s.act();
        assertNull(s.getGrid());
        assertTrue((Main.grid).get(new Location(1, 23)) instanceof Score);
    }

    /**
     * Tests the toString method for score.
     */
    @Test
    void toString1() {
        Score s = new Score();
        assertEquals("  0 ", s.toString());
        s.inc(10);
        assertEquals(" 10 ", s.toString());
        s.inc(101);
        assertEquals("111", s.toString());
    }

}