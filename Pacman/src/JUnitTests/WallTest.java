package JUnitTests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import gridworld.actor.*;

class WallTest {

    @Test
    void Wall() {
        Wall w = new Wall();
        assertNull(w.getColor());
        assertTrue(true);
    }

    @Test
    void act() {
    }
}