package gridworld.actor;

import gridworld.grid.Location;


//Singleton class
public class Pacman extends MovableActor
{
    //    private static Pacman SINGLE_INSTANCE = null;

    protected static int count = 0;


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

        Actor other = grid.get( newLocation );
        if(other instanceof Ghost )
        {
            this.removeSelfFromGrid();
            return;
        }
        if ( other != null )
            other.removeSelfFromGrid();

        if ( count % 2 == 0 )
        {
            Pacman p = new Pacman();
            p.setDirection( this.getDirection() );
            p.putSelfInGrid( grid, newLocation );


            //            grid.put( newLocation, p); AVOID THIS
        }
        else
        {
            PacmanClosed p = new PacmanClosed();
            p.setDirection( this.getDirection() );
            p.putSelfInGrid( grid, newLocation );
            //            grid.put( newLocation,  p); AVOID THIS
        }
        grid.remove( location );
        location = newLocation;
        count++;

    }


    public void act()
    {
        if ( canMove() )
        {
            move();
        }

    }



}