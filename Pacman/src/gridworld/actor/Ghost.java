package gridworld.actor;

import gridworld.grid.Location;

import java.awt.*;


public class Ghost extends MovableActor
{
    public Ghost( Color color )
    {
        super();
        setColor( color );

    }


    @Override public void act()
    {
        if ( canMove() )
        {
            move();
        }
        else
        {
            turn();
        }
    }


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
        {
            if ( other instanceof PowerPellet )
            {
                PowerPellet p = new PowerPellet();
                other.removeSelfFromGrid();
                //  grid.put( location, p ); DONT USE THIS, <bold>use</bold>  putSelfInGrid
                p.putSelfInGrid( grid, location );
                grid.put( newLocation, this );
                location = newLocation;
                return;
            }
            if ( other instanceof Pellet )
            {
                Pellet p = new Pellet();
                other.removeSelfFromGrid();
                //  grid.put( location, p ); DONT USE THIS, <bold>use</bold>  putSelfInGrid
                p.putSelfInGrid( grid, location );
                grid.put( newLocation, this );
                location = newLocation;
                return;
            }

            other.removeSelfFromGrid();

        }
        location = newLocation;
        grid.put( location, this );
    }

}
