package JUnitTests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import gridworld.actor.*;

/**
 * This class tests the Wall class.
 */
class WallTest {

    /**
     * Tests the Wall constructor.
     */
    @Test
    void Wall() {
        Wall w = new Wall();
        assertNull(w.getColor());
    }

    /**
     * Tests the act method for Wall.
     */
    @Test
    void act() {
        Wall w = new Wall();
        w.act();
        assertTrue(true);
    }
}