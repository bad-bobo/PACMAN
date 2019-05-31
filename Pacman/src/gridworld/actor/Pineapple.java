package gridworld.actor;

import gridworld.grid.Grid;


/**
 * A pineApple is worth 500 points, in the game where you are tying to get the max points
 */
public class Pineapple extends Actor implements Food
{

    /**
     * Default contructor
     */
    public Pineapple()
    {
        setColor( null );
    }


    /**
     * increments the score by 250 when eaten
     *
     * @param grid the grid in which the object is
     */
    @Override public void eatAction( Grid grid )
    {
        Score.inc( 500 ); //500 points
    }


    /**
     * Stub
     */
    @Override public void act()
    {
    }
}
