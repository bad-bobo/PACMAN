package gridworld.actor;

import gridworld.grid.Grid;
import gridworld.grid.Location;
import project.Main;

import java.awt.*;
import java.util.ArrayList;



public  abstract class Ghost extends MovableActor
{

    /** The previous cell*/
    protected Location oldLocation = location;


    /**
     * Creates a new Ghost, sets color to color.
     * @param color The Color for Ghost.
     */
    public Ghost( Color color )
    {
        super();
        setColor( color );
    }


    /**
     * Gets the currrent Location of Pacman
     * @return
     */
    protected Location getPacmanLocation()
    {
        ArrayList<Location> actors = Main.grid.getOccupiedLocations();
        for ( Location loc : actors )
        {
            if ( Main.grid.get( loc ) instanceof Pacman )
            {
                return loc;
            }
        }

        return null;

    }

    /**
     * Moves the Ghost the the new Location by
     * 1) deleting this Ghost
     * 2) If the new Location hold no Actor, creates a new Ghost there
     * 2) If there is a pellet in the new Location, deletes the pellet and puts a pellet in this location
     * 3) Put a new Ghost in the new Location
     *
     * @param newLocation the new location
     */
    public void moveTo( Location newLocation )
    {
        if ( grid == null )
            throw new IllegalStateException( "This actor is not in a grid." );
        if ( grid.get( location ) != this )
            throw new IllegalStateException( "The grid contains a different actor at location " + location + "." );
        if ( !grid.isValid( newLocation ) )
        {
            throw new IllegalArgumentException( "Location " + newLocation + " is not valid." );
        }

        if ( newLocation.equals( location ) )
            return;
        grid.remove( location );
        Actor other = grid.get( newLocation );
        if ( other != null )
        {

            other.removeSelfFromGrid();

        }
        oldLocation = location;
        location = newLocation;
        grid.put( location, this );
    }


    /**
     * A Ghost  can move anywhere except if the next location has a wall or another Ghost
     * @param next  The next Location
     * @return  True if can move, false otherwise.
     */
    public boolean canMove( Location next )
    {

        Grid<Actor> gr = getGrid();
        if ( gr == null )
            return false;

        if ( !gr.isValid( next ) )
            return false;
        Actor neighbor = gr.get( next );

        if ( next.equals( oldLocation ) )
        {
            return false;
        }
        if ( neighbor instanceof Wall )
        {
            return false;
        }

        return !( neighbor instanceof Ghost );
    }



}
