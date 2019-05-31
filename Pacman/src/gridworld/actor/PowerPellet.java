package gridworld.actor;

import gridworld.grid.Grid;

import java.awt.*;


/**
 * When eaten by pacman, makes the Ghosts idle
 */
public class PowerPellet extends Actor implements Food
{
    /**
     * Default constructor, sets color to blue
     */
    public PowerPellet()
    {
        super();
        setColor(new Color(150,250,255) );
    }


    /**
     * Scares the ghost
     * @param grid the grid in which the object is
     */
    public void eatAction( Grid grid )
    {
        Ghost.scare();
    }


    /**
     * Stub
     */
    public void act()
    {

    }
}
