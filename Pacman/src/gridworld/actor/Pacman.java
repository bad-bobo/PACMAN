package gridworld.actor;

import gridworld.grid.Location;


//Singleton class
public class Pacman extends MovableActor
{
    //    private static Pacman SINGLE_INSTANCE = null;


    public Pacman()
    {
        super();
    }

    //    public static Pacman getInstance()
    //    {
    //        if ( SINGLE_INSTANCE == null )
    //        {
    //            synchronized ( Pacman.class )
    //            {
    //                if ( SINGLE_INSTANCE == null )
    //                {
    //                    SINGLE_INSTANCE = new Pacman();
    //                }
    //            }
    //        }
    //        return SINGLE_INSTANCE;
    //    }


    /**
     * Moves this actor to a new location. If there is another actor at the
     * given location, it is removed. <br />
     * Precondition: (1) This actor is contained in a grid (2)
     * <code>newLocation</code> is valid in the grid of this actor
     *
     * @param newLocation the new location
     */
    public void moveTo( Location newLocation )
    {
        if ( grid == null )
            throw new IllegalStateException( "This actor is not in a grid." );
        if ( grid.get( location ) != this )
            throw new IllegalStateException(
                            "The grid contains a different actor at location " + location + "." );
        if ( !grid.isValid( newLocation ) )
        {
            throw new IllegalArgumentException( "Location " + newLocation + " is not valid." );
        }

        if ( newLocation.equals( location ) )
            return;
        grid.remove( location );
        Actor other = grid.get( newLocation );
        if ( other != null )
            other.removeSelfFromGrid();
        location = newLocation;
        grid.put( location, this );
    }


    public void act()
    {
        if(canMove())
        {
            move();
        }

    }

}
