package project;

import gridworld.actor.*;
import gridworld.grid.BoundedGrid;
import gridworld.grid.Location;
import gridworld.world.PacmanWorld;

import java.awt.*;


public class Main
{

    private static final int row = 30;

    private static final int col = 30;


    public static void main( String[] args )
    {
        BoundedGrid grid = new BoundedGrid( row, col );
        PacmanWorld world = new PacmanWorld( grid );

        Pacman pacman = new Pacman();
        Ghost ghost1 = new Ghost( Color.PINK );
        Wall wall = new Wall();
        Pellet pel = new Pellet();
        PowerPellet powPel = new PowerPellet();

        pacman.putSelfInGrid( grid, new Location( 4, 4 ) );
        ghost1.putSelfInGrid( grid, new Location( 3, 3 ) );
        wall.putSelfInGrid( grid, new Location( 0, 0 ) );
        pel.putSelfInGrid( grid, new Location( 1, 1 ) );
        powPel.putSelfInGrid( grid, new Location( 2, 2 ) );

        world.show();
    }
}
