package JUnitTests;

import gridworld.actor.Dijkstra;
import gridworld.actor.Pacman;
import gridworld.grid.BoundedGrid;
import gridworld.grid.Location;
import gridworld.world.PacmanWorld;
import org.junit.jupiter.api.Test;
import project.Main;
import project.Mechanics;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DijkstraTest
{

    @Test void act()
    {

        ArrayList<Location> expectedPath = new ArrayList<Location>( 19 );
        {
            expectedPath.add( new Location( 21, 22 ) );
            expectedPath.add( new Location( 21, 21 ) );
            expectedPath.add( new Location( 21, 20 ) );
            expectedPath.add( new Location( 21, 19 ) );
            expectedPath.add( new Location( 21, 18 ) );
            expectedPath.add( new Location( 21, 17 ) );
            expectedPath.add( new Location( 20, 17 ) );
            expectedPath.add( new Location( 19, 17 ) );
            expectedPath.add( new Location( 19, 16 ) );
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

        dijkstra.act();
        ArrayList<Location> actualPath = Mechanics.convertNodeToLocations( ( dijkstra.getPath() ) );

        //Testing act path
        assertEquals( expectedPath, actualPath );

        //Testing visualization
        {
            //Already done in CasperTest.java, they both inherit the same method.
        }
    }
}