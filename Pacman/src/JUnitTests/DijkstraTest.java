package JUnitTests;

import gridworld.actor.Casper;
import gridworld.actor.Dijkstra;
import gridworld.actor.Ghost;
import gridworld.actor.Pacman;
import gridworld.grid.BoundedGrid;
import gridworld.grid.Location;
import gridworld.world.PacmanWorld;
import org.junit.jupiter.api.Test;
import project.Main;
import project.Mechanics;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class DijkstraTest
{

    @Test void act()
    {

        Ghost.clearScaredTimer();
        ArrayList<Location> expectedPath = new ArrayList<Location>( 19 );
        {
//            expectedPath.add( new Location( 21, 22 ) );
            expectedPath.add( new Location( 21, 21 ) );
            expectedPath.add( new Location( 21, 20 ) );
            expectedPath.add( new Location( 21, 19 ) );
            expectedPath.add( new Location( 21, 18 ) );
            expectedPath.add( new Location( 21, 17 ) );
            expectedPath.add( new Location( 20, 17 ) );
            expectedPath.add( new Location( 19, 17 ) );
            expectedPath.add( new Location( 19, 16 ) );
            expectedPath.add( new Location( 19, 15 ) );
            expectedPath.add( new Location( 19, 14 ) );
            expectedPath.add( new Location( 19, 13 ) );
            expectedPath.add( new Location( 20, 13 ) );
            expectedPath.add( new Location( 20, 12 ) );
            expectedPath.add( new Location( 20, 11 ) );

        }

        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );
        Main.currentLevel = 1;
        Mechanics.initGrid( Mechanics.loadFile( "Map_level1", Main.ROW, Main.COL, "" ), grid );
        PacmanWorld world = new PacmanWorld( grid );

        Dijkstra dijkstra = new Dijkstra( 1 );
        dijkstra.putSelfInGrid( grid, new Location( 22, 22 ) );

        //Path should be null b/c no path is made
        assertEquals( dijkstra.getPath().size(), 0 );

        Pacman pacman = new Pacman();
        pacman.putSelfInGrid( grid, new Location( 20, 11 ) );

        world.show();
//        Mechanics.sleep( 1000 );

        assertFalse( Dijkstra.isScared() );

        dijkstra.act();
//        Mechanics.sleep( 10000 );
//        System.out.println( dijkstra.getPath() );
        ArrayList<Location> actualPath = Mechanics.convertNodeToLocations( ( dijkstra.getPath() ) );

        //Testing act path
        assertEquals( expectedPath, actualPath );

        //Testing visualization
        {
            //Already done in CasperTest.java, they both inherit the same method.
        }


        Dijkstra.clearScaredTimer();
        Dijkstra.scare();
        dijkstra.setColor( Color.BLACK );
        dijkstra.act();
        dijkstra.act();

        Casper.clearScaredTimer();

        dijkstra.removeSelfFromGrid();
        dijkstra.putSelfInGrid( grid, new Location( 22, 22 ) );
        pacman.removeSelfFromGrid();
        pacman.putSelfInGrid( grid, new Location( 12, 1 ) );

        for ( int i = 0; i < 30; i++ )
        {
            dijkstra.act();
        }
    }
}