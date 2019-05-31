package JUnitTests;

import gridworld.actor.*;
import gridworld.grid.BoundedGrid;
import gridworld.grid.Location;
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

        // Visually check
        Bush bush1 = new Bush(1);
        bush1.eatAction( grid );


        Bush bush2 = new Bush( 2 );
        bush2.eatAction( grid );


        Bush bush3 = new Bush( 3 );
        bush3.eatAction( grid );


        bush1.act();//stub

    }


    @Test void act()
    {

    }



}