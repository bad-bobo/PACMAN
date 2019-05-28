package gridworld.actor;

import gridworld.grid.Grid;

import java.awt.*;


/**
 * When eaten by pacman, makes the Ghosts Scatter
 */
public class PowerPellet extends Actor implements Edible
{
    public PowerPellet()
    {
        super();
        setColor(new Color(150,250,255) );
    }


    public void action( Grid grid )
    {
        Ghost.scare();
    }


    public void act()
    {

    }
}
