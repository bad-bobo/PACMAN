package JUnitTests;
import gridworld.world.World;
import gridworld.actor.*;
import gridworld.grid.*;
import gridworld.actor.Casper;
import gridworld.actor.Pacman;
import gridworld.world.PacmanWorld;
import project.Main;
import project.Mechanics;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the class PacmanWorld
 */
class PacmanWorldTest {

    @org.junit.jupiter.api.Test

    /*
    Tests the one method in PacmanWorldTest
     */
    void keyPressed() {
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        PacmanWorld pw = new PacmanWorld(grid);
        String[] keys = {"W", "A", "S", "D", "RIGHT", "LEFT", "UP", "DOWN"};
        int[] l = {Location.NORTH, Location.WEST, Location.SOUTH, Location.EAST, Location.WEST, Location.EAST,
                Location.NORTH, Location.SOUTH};
        int i = 0;
        for ( String s : keys)
        {
            pw.keyPressed(s, Mechanics.getPacmanLocation(grid));
            assertEquals(Pacman.newDirection, l[i]);
            i++;
        }

    }
}