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
        int[] locs = {Location.NORTH, Location.WEST, Location.SOUTH, Location.EAST, Location.WEST, Location.EAST,
                Location.NORTH, Location.SOUTH};
        Pacman pacman = new Pacman();
        pacman.putSelfInGrid( grid , new Location(20, 11) );
        int i = 0;
        for ( String string : keys)
        {
            pw.keyPressed(string, Mechanics.getPacmanLocation(grid));
            Pacman.currentDirection = locs[i];
            assertEquals(locs[i],  Pacman.currentDirection);
            i++;
//            System.out.println( i );
        }

    }
}