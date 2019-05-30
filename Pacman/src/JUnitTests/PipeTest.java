package JUnitTests;

import org.junit.jupiter.api.Test;
import gridworld.actor.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the Pipe class.
 */
class PipeTest {

    /**
     * Tests the Pipe constructor.
     */
    @Test
    void Pipe() {
        Pipe p = new Pipe();
        assertNull(p.getColor());
        assertNull(p.getGrid());
    }

    /**
     * Tests the act method for Pipe.
     */
    @Test
    void act() {
        Pipe p = new Pipe();
        p.act();
        assertTrue(true);
    }
}