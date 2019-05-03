package project;

import gridworld.actor.*;
import gridworld.grid.BoundedGrid;
import gridworld.grid.Location;

import java.awt.*;


public class Main
{

    private static final int row = 30 ;

    private static final int anInt = 30;

    private static final int col = anInt;


    public static void main( String[] args )
    {
        BoundedGrid grid = new BoundedGrid( row, col );
        ActorWorld world = new ActorWorld( grid );

        Ghost g1 = new Ghost( Color.PINK );
        Ghost g2 = new Ghost( Color.CYAN );
        PowerPellet powPel1 = new PowerPellet();
        Pacman pacman = new Pacman();
        Pellet pel = new Pellet();
        Wall w1 = new Wall();

        w1.putSelfInGrid( grid, new Location( 4, 4 ) );
        pel.putSelfInGrid( grid, new Location( 5, 5 ) );
        powPel1.putSelfInGrid( grid, new Location( 3, 3 ) );
        g2.putSelfInGrid( grid, new Location( 6, 6 ) );
        pacman.putSelfInGrid( grid, new Location( 1, 1 ) );
        g1.putSelfInGrid( grid, new Location( 0, 0 ) );

        world.show();
    }
}
