package gridworld.actor;

import gridworld.grid.Grid;

import java.awt.*;


/**
 * A pellet can be eaten only by Pacman and increases the score
 */
public class Pellet extends Actor implements Edible
{
    /**
     * Creates a new Pellet, sets the color to yello
     */
    public Pellet()
    {
        super();
        setColor( new Color( 247, 255, 160 ) );
    }


    /**
     * Does  nothing
     */
    public void act()
    {

    }


    /**
     * Increments the score by 1.
     * @param grid
     */
    public void action( Grid grid )
    {
        Score.inc();
    }

}
