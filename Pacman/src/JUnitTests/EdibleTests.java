package JUnitTests;

import gridworld.actor.*;
import gridworld.grid.BoundedGrid;
import gridworld.grid.Location;
import org.junit.jupiter.api.Test;
import project.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EdibleTests
{
    @Test void pineapple()
    {
        Score.clear();
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );

        Pineapple pineapple = new Pineapple();
        pineapple.eatAction( grid );
        assertEquals( 500, Score.score );
    }


    @Test void pellet()
    {
        Score.clear();
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );

        Pellet pellet = new Pellet();
        pellet.eatAction( grid );
        assertEquals( 1, Score.score );

        //Stub
        pellet.act();
    }


    @Test void bush()
    {
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );

        Bush bush = new Bush( 1 );
        Wall wall = new Wall();
        wall.putSelfInGrid( grid, new Location( 20, 11 ) );
        bush.eatAction( grid );
        assertEquals( Pacman.class, grid.get( new Location( 20, 11 ) ).getClass() );
        assertEquals( Inky.class, grid.get( new Location( 22, 22 ) ).getClass() );
        assertEquals( Inky.class, grid.get( new Location( 10, 10 ) ).getClass() );
        assertEquals( Inky.class, grid.get( new Location( 3, 2 ) ).getClass() );
    }


    @Test void powerPellet()
    {
        BoundedGrid grid = new BoundedGrid( Main.ROW, Main.COL );

        PowerPellet pp = new PowerPellet();
        assertEquals( false, Ghost.isScared() );
        pp.eatAction( grid );
        assertEquals( true, Ghost.isScared() );

        //Stub
        pp.act();
    }

}
