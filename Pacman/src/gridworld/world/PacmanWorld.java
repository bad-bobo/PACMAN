package gridworld.world;

import gridworld.actor.Actor;
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
//        System.out.println( "PacmanWorld.keyPressed: " + KeyStroke.getKeyStroke( description ) );
        switch ( KeyStroke.getKeyStroke( description ).toString() )
        {
            case "pressed W":
                Pacman.newDirection=Location.NORTH;
                //pacmanKeyHelper( actors, grid, Location.NORTH );
                return true;
            case "pressed D":
                Pacman.newDirection=Location.EAST;
                //pacmanKeyHelper( actors, grid, Location.EAST );
                return true;
            case "pressed S":
                Pacman.newDirection=Location.SOUTH;
                //pacmanKeyHelper( actors, grid, Location.SOUTH );
                return true;
            case "pressed A":
                Pacman.newDirection=Location.WEST;
                //pacmanKeyHelper( actors, grid, Location.WEST );
                return true;
            case "pressed UP":
                Pacman.newDirection=Location.NORTH;
                //pacmanKeyHelper( actors, grid, Location.NORTH );
                return true;
            case "pressed RIGHT":
                Pacman.newDirection=Location.EAST;
                //pacmanKeyHelper( actors, grid, Location.EAST );
                return true;
            case "pressed DOWN":
                Pacman.newDirection=Location.SOUTH;
                //pacmanKeyHelper( actors, grid, Location.SOUTH );
                return true;
            case "pressed LEFT":
                Pacman.newDirection=Location.WEST;
                //pacmanKeyHelper( actors, grid, Location.WEST );
                return true;

            default:
                return false;

        }

    }


    private void score()
    {
        ArrayList<Location> actors = getGrid().getOccupiedLocations();

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
