package JUnitTests;

import gridworld.actor.*;
import gridworld.grid.BoundedGrid;
import gridworld.world.PacmanWorld;
import org.junit.jupiter.api.Test;
import project.Main;
import project.Mechanics;

import static org.junit.jupiter.api.Assertions.*;


class BushTest
{

    @Test void eatAction()
    {
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        Main.currentLevel = 1;
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        PacmanWorld world = new PacmanWorld( grid );

        //Level1
        Bush bush1 = new Bush(1);

    }


    @Test void act()
    {
        //Stub, nothing to test
    }
}