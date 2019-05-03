package gridworld.actor;

import gridworld.grid.Grid;
import gridworld.grid.Location;


public abstract class MovableActor extends Actor
{
    public MovableActor()
    {
        super();
    }

    /**
     * Gets the current direction of this actor.
     * @return the direction of this actor, an angle between 0 and 359 degrees
     */
    public int getDirection()
    {
        return direction;
    }

    /**
     * Sets the current direction of this actor.
     * @param newDirection the new direction. The direction of this actor is set
     * to the angle between 0 and 359 degrees that is equivalent to
     * <code>newDirection</code>.
     */
    public void setDirection(int newDirection)
    {
        direction = newDirection % Location.FULL_CIRCLE;
        if (direction < 0)
            direction += Location.FULL_CIRCLE;
    }
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
            throw new IllegalArgumentException( "Location " + newLocation + " is not valid." );

        if ( newLocation.equals( location ) )
            return;
        grid.remove( location );
        Actor other = grid.get( newLocation );
        if ( other != null )
            other.removeSelfFromGrid();
        location = newLocation;
        grid.put( location, this );
    }


    /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection( getDirection() + Location.RIGHT );
    }


    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if ( gr == null )
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation( getDirection() );
        if ( gr.isValid( next ) )
            moveTo( next );
        else
            removeSelfFromGrid();
    }


    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     *
     * @return true if this bug can move.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if ( gr == null )
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation( getDirection() );
        if ( !gr.isValid( next ) )
            return false;
        Actor neighbor = gr.get( next );
        // return ( neighbor == null ) || ( neighbor instanceof Flower );
        return ( !(neighbor instanceof Wall) );
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }

}
