package gridworld.world;

import gridworld.actor.Actor;
import gridworld.world.ActorWorld;
import gridworld.actor.Pacman;
import gridworld.grid.Grid;
import gridworld.grid.Location;

import javax.swing.*;
import java.util.ArrayList;


public class PacmanWorld extends ActorWorld
{
    public PacmanWorld( Grid<Actor> g )
    {
        super( g );
    }


    @Override public boolean keyPressed( String description, Location loc )
    {
        ArrayList<Location> actors = getGrid().getOccupiedLocations();
        Grid<Actor> grid = getGrid();
        System.out.println( "PacmanWorld.keyPressed: " + KeyStroke.getKeyStroke(
                        description ) );
        switch ( KeyStroke.getKeyStroke( description ).toString() )
        {
            case "pressed W":
                pacmanKeyHelper( actors, grid, Location.NORTH );
                return true;
            case "pressed D":
                pacmanKeyHelper( actors, grid, Location.EAST );
                return true;
            case "pressed S":
                pacmanKeyHelper( actors, grid, Location.SOUTH );
                return true;
            case "pressed A":
                pacmanKeyHelper( actors, grid, Location.WEST );
                return true;

            default:
                return false;

        }

    }


    private void pacmanKeyHelper(
                    ArrayList<Location> actors, Grid<Actor> grid, int loc )
    {
        for ( Location l : actors )
        {
            if ( grid.get( l ) instanceof Pacman )
            {
                Pacman pacman = ( (Pacman)grid.get( l ) );
                pacman.setDirection( loc );
                
            }
        }

    }
}
