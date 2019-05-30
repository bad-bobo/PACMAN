package gridworld.world;

import gridworld.actor.Actor;
import gridworld.actor.Pacman;
import gridworld.grid.Grid;
import gridworld.grid.Location;

import javax.swing.*;
import java.util.ArrayList;



public class PacmanWorld extends ActorWorld
{
    /**
     * Creates a new PacmanWorld from a given grid
     * @param g the given grid
     */
    public PacmanWorld( Grid<Actor> g )
    {
        super( g );
    }

    /**
     * This uses the key that is pressed on the keyboard to set the direction of Pacman.
     * @param description the string describing the key, in
     *                    <a href="http://java.sun.com/javase/6/docs/api/javax/swing/KeyStroke.html#getKeyStroke
     *                    (java.lang.String)">this format</a>.
     * @param loc         the selected location in the grid at the time the key was pressed
     * @return true if a valid location key was pressed, false otherwise
     */
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
