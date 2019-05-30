package JUnitTests;

import org.junit.jupiter.api.Test;
import gridworld.actor.*;
import gridworld.grid.*;
import project.Mechanics.*;
import project.Main.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the MovableActor class.
 */
class MovableActorTest {

    /**
     * This tests the MovableActor constructor.
     */
    @Test
    void MovableActor() {
        MovableActor ma = new Pacman();
        assertNotNull(ma);
        assertNotNull(ma.getDirection());
    }

    /**
     * This tests the getDirection method from MovableActor.
     */
    @Test
    void getDirection() {
        MovableActor ma = new Pacman();
        assertEquals(0, ma.getDirection());
        ma.setDirection(359);
        assertEquals(359, ma.getDirection());
    }

    /**
     * This tests the setDirection method from MovableActor.
     */
    @Test
    void setDirection() {
        MovableActor ma = new Pacman();
        ma.setDirection(90);
        assertEquals(90, ma.getDirection());
    }
}