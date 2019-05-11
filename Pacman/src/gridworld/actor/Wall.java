package gridworld.actor;

/**
 * No actor can break a wall.
 */
public class Wall extends Actor
{
    /**
     * Creates new wall, sets color to null for actual gif color
     */
    public Wall()
    {
        super();
        setColor( null );

    }


    /**
     * Does nothing
     */
    public void act()
    {

    }

}
