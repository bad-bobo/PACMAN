package gridworld.actor;

import gridworld.grid.Grid;

import java.awt.*;


/**
 * When eaten by pacman, makes the Ghosts Scatter
 */
public class PowerPellet extends Actor implements Food
{
    public PowerPellet()
    {
        super();
        setColor(new Color(150,250,255) );
    }


    public void eatAction( Grid grid )
    {
        Ghost.scare();
    }


    public void act()
    {

    }
}
