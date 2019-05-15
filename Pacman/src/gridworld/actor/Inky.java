package gridworld.actor;

import gridworld.grid.Location;

import java.awt.*;


/**
 * Inky is a simple Ghost, Inky goes anywhere
 * TODO: Soham
 */
public class Inky extends Ghost
{

    public Inky()
    {
        super( Color.BLUE );

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
        if(grid.get( newLocation ) instanceof Ghost)
        {
            removeSelfFromGrid();
        }
        super.moveTo( newLocation );
    }


    /**
     * Randomly chooses a location to move
     */
    @Override public void act()
    {
        while ( true )
        {
            int rand = (int)( Math.random() * ( 4 ) );

            switch ( rand )
            {
                case 0:
                    if ( canMove( new Location( location.getRow() + 1,
                                    location.getCol() ) ) )
                    {
                        moveTo( new Location( location.getRow() + 1,
                                        location.getCol() ) );
                        return;
                    }
                    break;

                case 1:
                    if ( canMove( new Location( location.getRow() - 1,
                                    location.getCol() ) ) )
                    {
                        moveTo( new Location( location.getRow() - 1,
                                        location.getCol() ) );
                        return;
                    }
                    break;
                case 2:
                    if ( canMove( new Location( location.getRow(),
                                    location.getCol() + 1 ) ) )
                    {
                        moveTo( new Location( location.getRow(),
                                        location.getCol() + 1 ) );
                        return;
                    }
                    break;
                case 3:
                    if ( canMove( new Location( location.getRow(),
                                    location.getCol() - 1 ) ) )
                    {
                        moveTo( new Location( location.getRow(),
                                        location.getCol() - 1 ) );
                        return;
                    }
                    break;

            }
        }

    }

}
