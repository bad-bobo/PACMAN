package gridworld.actor;

import gridworld.grid.Grid;
import gridworld.grid.Location;


/**
 * Subclass of actors. Represents an actors that can move.
 */
public abstract class MovableActor extends Actor
{
    public MovableActor()
    {
        super();
    }


    /**
     * Gets the current direction of this actor.
     *
     * @return the direction of this actor, an angle between 0 and 359 degrees
     */
    public int getDirection()
    {
        return direction;
    }


    /**
     * Sets the current direction of this actor.
     *
     * @param newDirection the new direction. The direction of this actor is set
     *                     to the angle between 0 and 359 degrees that is equivalent to
     *                     <code>newDirection</code>.
     */
    public void setDirection( int newDirection )
    {
        direction = newDirection;
        if ( direction < 0 )
            direction += Location.FULL_CIRCLE;
        if (direction > 359)
            direction -= Location.FULL_CIRCLE;
    }


    /**
     * Moves this actor to a new location. If there is another actor at the
     * given location, it is removed. <br />
     * Precondition: (1) This actor is contained in a grid (2)
     * <code>newLocation</code> is valid in the grid of this actor
     *
     * @param newLocation the new location
     */
    public abstract void moveTo( Location newLocation );

    /**
     * Moves this actor forward, if possible. Not implemented yet.
     */
    /**
     * Moves forward, if possible.
     * Moves this actor forward, if possible. Not implemented yet.
     */
    void move()
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
     * Overridden in all subclasses at the moment
     * @return true if can move,
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
        return ( !( neighbor instanceof Wall ) );

    }

}
