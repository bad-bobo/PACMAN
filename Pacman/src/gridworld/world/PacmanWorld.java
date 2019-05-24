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

        switch ( KeyStroke.getKeyStroke( description ).toString() )
        {
            case "pressed W":
                Pacman.newDirection=Location.NORTH;
                return true;
            case "pressed D":
                Pacman.newDirection=Location.EAST;
                return true;
            case "pressed S":
                Pacman.newDirection=Location.SOUTH;
                return true;
            case "pressed A":
                Pacman.newDirection=Location.WEST;
                return true;
            case "pressed UP":
                Pacman.newDirection=Location.NORTH;
                return true;
            case "pressed RIGHT":
                Pacman.newDirection=Location.EAST;
                return true;
            case "pressed DOWN":
                Pacman.newDirection=Location.SOUTH;
                return true;
            case "pressed LEFT":
                Pacman.newDirection=Location.WEST;
                return true;

            default:
                return false;

        }

    }
}
